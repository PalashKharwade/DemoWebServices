package com.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.common.Reader.ReaderFile;
import com.common.model.Business.MiniStamm;
import com.common.model.Business.PeerStamm;
import com.common.model.helperPackage.HelperCurrencie;
import com.common.model.master.Pricing;
import com.common.model.master.PricingUnderlying;
import com.common.service.FastCalculatorService;
import com.common.util.Utils;


@Service
public class FastCalculatorServiceImpl implements FastCalculatorService {

	@Autowired
	private Environment env;

	@Autowired
	private ApplicationArguments applicationArguments;

	

	@Override
	public List<MiniStamm> getCalcSideData() {

		ArrayList<String> debugList = new ArrayList<String>();
		ArrayList<PeerStamm> peers = null;
		List<MiniStamm> list = null;
		HashMap shortstamm2 = new HashMap<String, MiniStamm>();
		HashMap ministammCallsPuts = new HashMap<String, MiniStamm>();
		HashMap currenciepairs = new HashMap<String, String>();
		HashMap currenciepairsReverse = new HashMap<String, String>();
		HashMap indexes = null;

		ArrayList<Integer> auswahltypenImplVola = new ArrayList<Integer>();
		auswahltypenImplVola.add(16);
		auswahltypenImplVola.add(17);

		ArrayList<Integer> auswahltypenSide = new ArrayList<Integer>();
		auswahltypenSide.add(1);
		auswahltypenSide.add(2);
		auswahltypenSide.add(4);
		auswahltypenSide.add(7);
		auswahltypenSide.add(8);
		auswahltypenSide.add(31);

		boolean production = Boolean.parseBoolean(env.getProperty("isProduction"));
		String[] args = applicationArguments.getSourceArgs();
		if (!production) {
			String fp = env.getProperty("filePath");
			System.out.println(fp);
			list = Utils.readFileStamm(fp + "stammdaten_fast.csv");
			HelperCurrencie hb = ReaderFile.readBackHms(fp + "underlyingstammdaten.csv");
			currenciepairs = hb.getHm1();
			currenciepairsReverse = hb.getHm3();
			indexes = hb.getHm2();
			shortstamm2 = Utils.readFileStammForHashMap2(list);
			peers = Utils.readPeers(fp + "peergroups.csv", fp + "stammdaten_fast.csv", shortstamm2);

		} else {
			System.out.println("args 1 " + args[1]);
			String fp = args[0];
			list = Utils.readFileStamm(fp + args[1]);
			// currenciepairs = FastCalculator.readCurrenciepairs(fp +
			// "underlyingstammdaten.csv");

			HelperCurrencie hb = ReaderFile.readBackHms(fp + "underlyingstammdaten.csv");
			currenciepairs = hb.getHm1();
			currenciepairsReverse = hb.getHm3();
			indexes = hb.getHm2(); // indexes
			shortstamm2 = Utils.readFileStammForHashMap2(list);
			peers = Utils.readPeers(fp + "peergroups.csv", fp + args[1], shortstamm2);

		}

		HashMap prices = new HashMap<String, Pricing>();
		HashMap currencies = new HashMap<String, Pricing>();
		// HashMap peergroups = new HashMap<String, Pricing>();
		HashMap underlyingprices = new HashMap<String, PricingUnderlying>();
		HashMap underlyingpricesIndex = new HashMap<String, PricingUnderlying>();
		HashMap preunderlyingprices = new HashMap<String, PricingUnderlying>();
		HashMap preunderlyingpricesIndex = new HashMap<String, PricingUnderlying>();
		/*
		 * HashMap underlyingCurrenciepricesUSD = new HashMap<String,
		 * PricingUnderlying>(); HashMap preunderlyingCurrenciepricesUSD = new
		 * HashMap<String, PricingUnderlying>(); HashMap underlyingCurrenciepricesEUR =
		 * new HashMap<String, PricingUnderlying>(); HashMap
		 * preunderlyingCurrenciepricesEUR = new HashMap<String, PricingUnderlying>();
		 */
		HashMap<String, PricingUnderlying> hc = null;

		List<MiniStamm> choicelistSide = list.stream().filter(d -> auswahltypenSide.contains(d.getTyp()))
				.collect(Collectors.toList());

		if (!production) {

			String fp = env.getProperty("filePath");
			String tradegate = Utils.getLastFile(fp + "aktuelleDatenTradegate.csv");

			currencies = Utils.readPricingFxVWD(fp + "fxvwd920.csv");
			hc = ReaderFile.readPricingUnderlyingsCurrencies(fp + "fxvwd920.csv", currencies, currenciepairs);

			prices = Utils.readPricing(fp + "ergebnisSTG1328.csv");
			underlyingprices = Utils.readPricingUnderlyings(fp + "tradegate2053.csv", preunderlyingprices);
			underlyingpricesIndex = Utils.readPricingUnderlyingsIndex(fp + "tradegate_index1088.csv",
					preunderlyingpricesIndex);

		} else {
			String fp = args[0];
			String euwax = Utils.getLastFile(fp + "aktuelleDatenEuwax.csv");
			String tradegate = Utils.getLastFile(fp + "aktuelleDatenTradegate.csv");
			String tradegateIndizes = Utils.getLastFile(fp + "aktuelleDatenTradegateIndizes.csv");
			String currenciesfxvwd = Utils.getLastFile(fp + "aktuelleDatenFxvwd.csv");

			String[] fileinfos = { euwax, tradegate, tradegateIndizes, currenciesfxvwd };

			currencies = Utils.readPricingFxVWD(currenciesfxvwd);
			hc = ReaderFile.readPricingUnderlyingsCurrencies(currenciesfxvwd, currencies, currenciepairs);

			prices = Utils.readPricing(euwax);
			underlyingprices = Utils.readPricingUnderlyings(tradegate, preunderlyingprices);
			underlyingpricesIndex = Utils.readPricingUnderlyingsIndex(tradegateIndizes,
					preunderlyingpricesIndex);
		}

		List<MiniStamm> resultlistSide = Utils.calcSide(choicelistSide, prices, underlyingprices, underlyingpricesIndex,
				currencies, debugList, hc, currenciepairs, indexes, currenciepairsReverse);

		return resultlistSide;
	}

}
