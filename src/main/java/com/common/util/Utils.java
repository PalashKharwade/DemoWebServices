package com.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.common.model.Business.MiniStamm;
import com.common.model.Business.PeerStamm;
import com.common.model.helperPackage.MeasureCalculation;
import com.common.model.helperPackage.ShortSideCalc;
import com.common.model.master.Pricing;
import com.common.model.master.PricingUnderlying;


public class Utils {

	public static HashMap readFileStammForHashMap2(List<MiniStamm> list) {
//		HashMap<String, MiniStamm> st = new HashMap<String, MiniStamm>();
//1424908
		HashMap<String, MiniStamm> st = list.stream()
			    .collect(Collectors.toMap(MiniStamm::getWkn, item -> item, (existing, replacement) -> replacement, HashMap::new));
		System.out.println("Actuall St size : "+st.size());
		
			return st;
//		for (MiniStamm item : list) {
//			st.put(item.getWkn(), item);
//		}
//		System.out.println("Actuall St size : "+st.size());
//		return st;
			
	}

	public static ArrayList<PeerStamm> readPeers(String filename, String stamm, HashMap shortstamm) {

		String vals[];
		String val;
		ArrayList<PeerStamm> p = new ArrayList<PeerStamm>();

		try {
			HashMap st = new HashMap<String, PeerStamm>();
			File filestamm = new File(stamm);
			BufferedReader readerstamm = new BufferedReader(new FileReader(filestamm));

			while (readerstamm.ready()) {
				val = readerstamm.readLine();
				vals = val.split(";");
				if (vals.length > 12) {
					if (vals[2].length() == 6 && vals[3].length() == 12) {
						if (vals[0].length() > 0)
							st.put(vals[2],
									new PeerStamm(vals[3], vals[2], vals[13], vals[12], Integer.parseInt(vals[0])));
					}
				}
			}
			

			File file = new File(filename);
			BufferedReader reader = new BufferedReader(new FileReader(file));

			long start = System.currentTimeMillis();
			System.out.println("Start:" + start);
			int counter = 0;

			while (reader.ready()) {
				val = reader.readLine();
				vals = val.split(";");

				if (st.containsKey(vals[0])) {

					PeerStamm ps = (PeerStamm) st.get(vals[0]);

					ArrayList<MiniStamm> pA = new ArrayList<MiniStamm>();
					for (int i = 0; i < vals.length; i++) {
						if (shortstamm.containsKey(vals[i])) {
							pA.add((MiniStamm) shortstamm.get(vals[i]));
						}
					}

					ps.setPeermembers(val);
					ps.setListPeermembers(pA);
					// System.out.println(pA);
					/*
					 * if(vals[0].equals("VX9LY2")) { for (int i = 0; i < vals.length; i++) {
					 * System.out.println(vals[i]); if(shortstamm.containsKey(vals[i])) {
					 * pA.add((ShortStamm)shortstamm.get(vals[i])); } } }
					 */

					p.add(ps);

				}
			}

			System.out.println("Anzahl Peergroups--->:" + p.size());

		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("ReedPeers Exception : " + e);
//			System.exit(0);
		}

		return p;
	
	}

	public static String getLastFile(String typoffile) {
		try {
			BufferedReader brTest = new BufferedReader(new FileReader(typoffile));
			return brTest.readLine();
		} catch (Exception e) {
			System.out.println("Fehler77" + e);
		}
		return "";
	}
	
	public static HashMap<String, Pricing> readPricingFxVWD(String filename) {
		HashMap<String, Pricing> pricing = new HashMap<String, Pricing>();

		String val = "";

		System.out.println(filename);

		try {
			File file = new File(filename);
			BufferedReader reader = new BufferedReader(new FileReader(file));

			int i = 0;
			String vals[];
			String cur = "";
			long start = System.currentTimeMillis();
			System.out.println("Start:" + start);

			while (reader.ready()) {
				val = reader.readLine();
				vals = val.split(";");
				String kombi[] = vals[7].split("\\.");
				if (kombi[0].length() > 3) {
					cur = kombi[0].substring(3);
				} else {
					cur = "USD";
				}
				pricing.put(cur, new Pricing(cur, new BigDecimal(vals[2]), new BigDecimal(vals[4])));
			}
			long end = System.currentTimeMillis();
			System.out.println("filename: " + filename + " i:" + i + " Dauer:" + (end - start) / 1000);
		} catch (Exception e) {
			System.out.println("Fehler4: " + val + " : : " + e);
		}
		return pricing;
	}

	public static HashMap<String, Pricing> readPricing(String filename) {
		// reads the prices from derivate
		HashMap<String, Pricing> pricing = new HashMap<String, Pricing>();
		String val = "";

		System.out.println(filename);
		int i = 0;
		int l = 0;

		try {
			File file = new File(filename);
			BufferedReader reader = new BufferedReader(new FileReader(file));

			String vals[];
			long start = System.currentTimeMillis();
			System.out.println("Start:" + start);

			while (reader.ready()) {
				val = reader.readLine();
				l = val.length();
				if (l > 10) {
					vals = val.split(";");
					if (vals[0].length() == 6) {
						if (vals.length == 5 && vals[3].length() > 6 && vals[1].length() > 0) {
							pricing.put(vals[0], new Pricing(vals[0], new BigDecimal(vals[1]), new BigDecimal(vals[4]),
									Integer.parseInt(vals[3]), Integer.parseInt(vals[2])));
						}
					}
				}
				i++;
			}
			long end = System.currentTimeMillis();
			System.out.println("filename: " + filename + " i:" + i + " Dauer:" + (end - start) / 1000);
		} catch (Exception e) {
			System.out.println("Fehler40006: " + val + " : : " + e + " Zeilennummer:" + i + " LängeString:" + l);
		}

		return pricing;
	}
	
	public static HashMap<String, PricingUnderlying> readPricingUnderlyings(String filename,
			HashMap<String, PricingUnderlying> prelist) {
		HashMap<String, PricingUnderlying> pricing = new HashMap<String, PricingUnderlying>();

		int i = 0;
		int laenge = 0;
		String underlyingnametmp = "";
		System.out.println("filename:" + filename);

		try {
			File file = new File(filename);
			BufferedReader reader = new BufferedReader(new FileReader(file));

			String vals[];
			String val;
			long start = System.currentTimeMillis();
			System.out.println("Start:" + start);
			while (reader.ready()) {
				val = reader.readLine();
				vals = val.split(";");
				laenge = vals.length;
				i++;

				if (vals.length > 5) {

					if (vals[0].length() == 12 && vals[2].length() > 0) {

						underlyingnametmp = vals[0];

						if (new BigDecimal(vals[2]).compareTo(BigDecimal.ZERO) > 0) {
							pricing.put(vals[0], new PricingUnderlying(vals[0],
									(new BigDecimal(vals[2]).add(new BigDecimal(vals[4]))).divide(new BigDecimal("2")),
									Integer.parseInt(vals[6])));
						} else {
							if (prelist.containsKey(vals[0])) {
								PricingUnderlying tmpUnderlying = (PricingUnderlying) prelist.get(pricing);
								pricing.put(vals[0], new PricingUnderlying(vals[0], tmpUnderlying.getAsk(),
										tmpUnderlying.getTimestamp()));
							} else {
								pricing.put(vals[0],
										new PricingUnderlying(vals[0],
												(new BigDecimal(vals[2]).add(new BigDecimal(vals[4])))
														.divide(new BigDecimal("2")),
												Integer.parseInt(vals[6])));
							}

						}
					}
				}
			}
			long end = System.currentTimeMillis();
			System.out.println("filename: " + filename + " i:" + i + " Dauer:" + (end - start) / 1000);
		} catch (Exception e) {
			System.out.println("Fehler3: " + i + " -->" + filename + " " + e + " Länge:" + laenge + " underlyingnametmp"
					+ underlyingnametmp);
			System.exit(0);
		}

		return pricing;
	}
	
	public static HashMap<String, PricingUnderlying> readPricingUnderlyingsIndex(String filename,
			HashMap<String, PricingUnderlying> prelist) {
		HashMap<String, PricingUnderlying> pricing = new HashMap<String, PricingUnderlying>();
		int i = 0;
		String tmpwert = "";
		System.out.println("Size of prelist" + prelist.size());

		try {
			File file = new File(filename);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String vals[];
			String val;
			int a = 0;
			long start = System.currentTimeMillis();
			System.out.println("StartIndizes:" + start);
			while (reader.ready()) {
				val = reader.readLine();
				tmpwert = val;
				i++;
				vals = val.split(";");
				if (vals.length > 4) {
					if (vals[2].length() > 0) {
						if (new BigDecimal(vals[2]).compareTo(BigDecimal.ZERO) > 0) {
							pricing.put(vals[0],
									new PricingUnderlying(vals[0], new BigDecimal(vals[2]), Integer.parseInt(vals[6])));
						}
					}
				}
				a++;
			}
			long end = System.currentTimeMillis();
			System.out.println("filename: " + filename + " i:" + i + " Dauer:" + (end - start) / 1000);
		} catch (Exception e) {
			System.out.println("Fehler11: " + filename + i + " " + e + " " + tmpwert);
			System.exit(0);
		}

		return pricing;
	}
	
	public static ArrayList<MiniStamm> readFileStamm(String filename) {
		ArrayList<MiniStamm> list = new ArrayList<MiniStamm>();
		int i = 0;
		String val = "";
		String vals[] = null;
		long start = System.currentTimeMillis();
		System.out.println("Start:" + start + " read now " + filename);

		try {
			File file = new File(filename);
			BufferedReader reader = new BufferedReader(new FileReader(file));

			int counter = 0;

			while (reader.ready()) {
				val = reader.readLine();
				vals = val.split(";");
				MiniStamm nt = null;

				if (vals.length > 9) {

					/*
					 * if(vals[8].length() > 0) { //Calls and Puts if(vals[0].equals("17")) { nt =
					 * new MiniStamm(Integer.parseInt(vals[0]), vals[1], vals[2], vals[3], vals[4],
					 * vals[5], new BigDecimal(vals[8]), new BigDecimal(vals[10]));
					 * nt.setQuanto(vals[6].equals("1")); list.add(nt); } }
					 */
					
					if (vals[9].length() > 0 && vals[8].length() > 0) { // Endless
						if (vals[0].equals("42") || vals[0].equals("43")) {
							if (vals[10].length() > 0) {
								nt = new MiniStamm(Integer.parseInt(vals[0]), vals[1], vals[2], vals[3], vals[4],
										vals[5], new BigDecimal(vals[8]), new BigDecimal(vals[10]));
								nt.setQuanto(vals[6].equals("1"));
								nt.setStrike(new BigDecimal(vals[9]));
								list.add(nt);
							}
						}
						if (vals[0].equals("40") || vals[0].equals("41")) {
							nt = new MiniStamm(Integer.parseInt(vals[0]), vals[1], vals[2], vals[3], vals[4], vals[5],
									new BigDecimal(vals[8]), new BigDecimal(vals[9]));
							nt.setQuanto(vals[6].equals("1"));
							list.add(nt);
						}
					   	if (vals[0].equals("22") || vals[0].equals("23")) {
							nt = new MiniStamm(Integer.parseInt(vals[0]), vals[1], vals[2], vals[3], vals[4], vals[5],
									new BigDecimal(vals[8]), new BigDecimal(vals[9]));
							nt.setQuanto(vals[6].equals("1"));
							if (vals[12].length() == 10) {
								nt.setStrike(new BigDecimal(vals[10]));
//								System.out.println("maturity Date : "+vals[12]);
								nt.setMaturity(LocalDate.parse(vals[12], DateTimeFormatter.ISO_LOCAL_DATE));
								list.add(nt);
							}
						}
						
					}
//					if (vals[9].length() > 0 && vals[8].length() > 0) { // Endless
//						
//					}
					if ((vals[0].equals("1") || vals[0].equals("2") || vals[0].equals("4")) && vals[8].length() > 0
							&& vals[11].length() > 0 && vals[12].length() == 10) { // Disco
						// System.out.println(vals[12]);
						nt = new MiniStamm(Integer.parseInt(vals[0]), vals[1], vals[2], vals[3], vals[4], vals[5],
								new BigDecimal(vals[8]), Double.valueOf(vals[11]));
						nt.setQuanto(vals[6].equals("1"));
						if (vals[0].equals("1")) {
							nt.setMaturity(LocalDate.parse(vals[12], DateTimeFormatter.ISO_LOCAL_DATE));
							list.add(nt);
						} else {
							if (vals[12].length() == 10 && vals[10].length() > 0) {
								nt.setBarriere(new BigDecimal(vals[10]));
								nt.setMaturity(LocalDate.parse(vals[12], DateTimeFormatter.ISO_LOCAL_DATE));
								list.add(nt);
							}
						}

					}

					if ((vals[0].equals("8") || vals[0].equals("7")) && vals[8].length() > 0 && vals[10].length() > 0
							&& vals[11].length() > 0 && vals[12].length() == 10) { // DiscoCall
						// Integer typ, String isin, String wkn, String u_isin, String emi, String
						// waehrung, BigDecimal bv, Double cap
						nt = new MiniStamm(Integer.parseInt(vals[0]), vals[1], vals[2], vals[3], vals[4], vals[5],
								new BigDecimal(vals[8]), Double.valueOf(vals[11]));
						nt.setQuanto(vals[6].equals("1"));
						nt.setMaturity(LocalDate.parse(vals[12], DateTimeFormatter.ISO_LOCAL_DATE));
						nt.setFloor(new BigDecimal(vals[10]));

						list.add(nt);
					}
				}
			}

			System.out.println("41:" + counter);
			long end = System.currentTimeMillis();
			System.out.println("filename: " + filename + " i:" + i + " Dauer:" + (end - start) / 1000);
		} catch (Exception e) {
			System.out.println("Fehler6: 10:" + vals[10]);
			System.out.println("Fehler6: 12:" + vals[12]);
			System.out.println("Fehler6: " + e);
//			System.out.println("Fehler6: " + val);
			System.exit(0);
		}
		
		return list;
	}
	
	public static List<MiniStamm> calcAddon(List<MiniStamm> choicelist, HashMap prices, HashMap underlyingprices,
			HashMap underlyingpricesIndex, HashMap currencies, ArrayList debugList, HashMap hc, HashMap currenciepairs,
			HashMap indexes, HashMap currenciepairsReverse) {
		List<MiniStamm> resultlist = new ArrayList<MiniStamm>();

		choicelist.stream().sequential().forEach(item -> {
			MiniStamm tmpStamm = item;
			boolean underlyingIsCurrency = false;

			if (prices.containsKey(tmpStamm.getWkn())
					&& (hc.containsKey(tmpStamm.getU_Isin()) || underlyingpricesIndex.containsKey(tmpStamm.getU_Isin())
							|| underlyingprices.containsKey(tmpStamm.getU_Isin()))) {

				PricingUnderlying tmpPricingUnderlying = null;
				Pricing tmpPricingUnderlyingCurrencie = null;
				Pricing tmpobj = (Pricing) prices.get(tmpStamm.getWkn());

				if (underlyingprices.containsKey(tmpStamm.getU_Isin())) {
					tmpPricingUnderlying = (PricingUnderlying) underlyingprices.get(tmpStamm.getU_Isin());
				}
				if (underlyingpricesIndex.containsKey(tmpStamm.getU_Isin())) {
					tmpPricingUnderlying = (PricingUnderlying) underlyingpricesIndex.get(tmpStamm.getU_Isin());
				}
				if (hc.containsKey(tmpStamm.getU_Isin())) {
					tmpPricingUnderlying = (PricingUnderlying) hc.get(tmpStamm.getU_Isin());
					underlyingIsCurrency = true;
				}

				if (tmpPricingUnderlying.getAsk().compareTo(BigDecimal.ZERO) > 0) {

					BigDecimal tmpStammAsk = null;
					BigDecimal tmpStammBid = null;
					BigDecimal addon = null;
					BigDecimal addonBid = null;
					BigDecimal addonMid = null;
					String currenciePartner = "";
					BigDecimal currenciePartnerValue = null;

					tmpStammAsk = tmpobj.getAsk();
					tmpStammBid = tmpobj.getBid();
					tmpStamm.setAsk(tmpobj.getAsk());
					tmpStamm.setBid(tmpobj.getBid());

					boolean curcalc = false;
					if (!underlyingIsCurrency) {
						if (!tmpStamm.getWaehrung().equals("EUR")) {
							Pricing cur = (Pricing) currencies.get(tmpStamm.getWaehrung());

							if (!tmpStamm.getQuanto()) {
								tmpStamm.setCurfaktor(cur.getAsk());

								tmpStamm.setAskCur(tmpStamm.getCurfaktor().multiply(tmpobj.getAsk()));
								tmpStamm.setBidCur(tmpStamm.getCurfaktor().multiply(tmpobj.getBid()));

								curcalc = true;
							}
						}
					}

					boolean yesaddon = false;
					boolean yesaddonBid = false;
					boolean yesaddonMid = false;

					tmpStamm.setAsk(tmpobj.getAsk());
					tmpStamm.setBid(tmpobj.getBid());

					if (tmpobj.getBid().compareTo(BigDecimal.ZERO) == 1
							&& tmpobj.getAsk().compareTo(BigDecimal.ZERO) == 1) {
						tmpStamm.setMid(
								(tmpobj.bid.add(tmpobj.ask)).divide(new BigDecimal("2.00"), 4, RoundingMode.HALF_UP));
						yesaddonMid = true;
					}

					tmpStamm.setUnderlyingprice(tmpPricingUnderlying.getAsk());

					if (!underlyingIsCurrency) {
						if (curcalc) {
							tmpStamm.setAsk(tmpobj.getAsk());
							tmpStamm.setBid(tmpobj.getBid());
							if (yesaddonMid) {
								tmpStamm.setMid(tmpStamm.getMid().multiply(tmpStamm.getCurfaktor()));
							}
							if (!indexes.containsKey(tmpStamm.getU_isin())) {
								tmpStamm.setUnderlyingprice(
										tmpPricingUnderlying.getAsk().multiply(tmpStamm.getCurfaktor()));
							}
						}
					}

					if (underlyingIsCurrency) {
						if (currenciepairsReverse.containsKey(tmpStamm.getU_isin())) {
							currenciePartner = currenciepairsReverse.get(tmpStamm.getU_isin()).toString().substring(3,
									6);
							if (currencies.containsKey(currenciePartner)) {
								currenciePartnerValue = ((Pricing) currencies.get(currenciePartner)).getAsk();
							}
						}
					}

					if (tmpobj.getAsk().compareTo(BigDecimal.ZERO) == 1) {
						addon = MeasureCalculation.calculateAddOn(tmpStamm.getTyp(), tmpStamm.getAsk(), item.getBv(),
								tmpStamm.getUnderlyingprice() /* tmpPricingUnderlying.getAsk() */, item.getKnockin(),
								item.getWkn(), underlyingIsCurrency, tmpStamm.getQuanto(), tmpStamm.getCurfaktor(),
								indexes.containsKey(tmpStamm.getU_isin()), currenciePartnerValue);
						yesaddon = true;
					}

					if (tmpobj.getBid().compareTo(BigDecimal.ZERO) == 1) {
						addonBid = MeasureCalculation.calculateAddOn(tmpStamm.getTyp(), tmpStamm.getBid(), item.getBv(),
								tmpStamm.getUnderlyingprice() /* tmpPricingUnderlying.getAsk() */, item.getKnockin(),
								item.getWkn(), underlyingIsCurrency, tmpStamm.getQuanto(), tmpStamm.getCurfaktor(),
								indexes.containsKey(tmpStamm.getU_isin()), currenciePartnerValue);
						yesaddonBid = true;
					}

					if (tmpobj.getBid().compareTo(BigDecimal.ZERO) == 1
							&& tmpobj.getAsk().compareTo(BigDecimal.ZERO) == 1 && yesaddonMid) {
						addonMid = MeasureCalculation.calculateAddOn(tmpStamm.getTyp(),
								(tmpStamm.getAsk().add(tmpStamm.getBid())).divide(new BigDecimal("2.00"), 4,
										RoundingMode.HALF_UP),
								item.getBv(), tmpStamm.getUnderlyingprice() /* tmpPricingUnderlying.getAsk() */,
								item.getKnockin(), item.getWkn(), underlyingIsCurrency, tmpStamm.getQuanto(),
								tmpStamm.getCurfaktor(), indexes.containsKey(tmpStamm.getU_isin()),
								currenciePartnerValue);
					}

					/*
					 * if(yesaddon && curcalc) { addon = addon.divide(tmpStamm.getCurfaktor(), 4,
					 * RoundingMode.HALF_UP); }
					 * 
					 * if(yesaddonBid && curcalc) { addonBid =
					 * addonBid.divide(tmpStamm.getCurfaktor(), 4, RoundingMode.HALF_UP); }
					 * 
					 * if(yesaddonMid && curcalc) { addonMid =
					 * addonMid.divide(tmpStamm.getCurfaktor(), 4, RoundingMode.HALF_UP); }
					 */

					tmpStamm.setAddon(addon);
					tmpStamm.setAddonBid(addonBid);

					if (yesaddonMid) {
						tmpStamm.setAddonMid(addonMid);
					}

					tmpStamm.setAbstand(
							tmpStamm.getKnockin().divide(tmpStamm.getUnderlyingprice(), 4, RoundingMode.HALF_UP)
									.subtract(new BigDecimal("1.00")));
					tmpStamm.setDerivativetime(tmpobj.getTimestamp());
					tmpStamm.setUnderlyingtime(tmpPricingUnderlying.getTimestamp());
					tmpStamm.setSize(tmpobj.getAsksize());
					tmpStamm.setAsk(tmpStammAsk);
					tmpStamm.setBid(tmpStammBid);

					resultlist.add(tmpStamm);
				}

			}
		});

		return resultlist;
	}
	
	public static List<MiniStamm> calcSide(List<MiniStamm> choicelist, HashMap prices, HashMap underlyingprices,
			HashMap underlyingpricesIndex, HashMap currencies, ArrayList debugList, HashMap hc, HashMap currenciepairs,
			HashMap indexes, HashMap currenciepairsReverse) {
		List<MiniStamm> resultlist = new ArrayList<MiniStamm>();
		// Map<String, Double> pr = new HashMap<String, Double>();

		try {

			System.out.println("Side - choicelist.size():" + choicelist.size());

			choicelist.stream().sequential().forEach(item -> {

				MiniStamm tmpStamm = item;
				boolean underlyingIsCurrency = false;

				if (prices.containsKey(item.getWkn())) {
					Pricing tmpobj = (Pricing) prices.get(item.getWkn());
					PricingUnderlying tmpPricingUnderlying = null;

					if (underlyingprices.containsKey(tmpStamm.getU_Isin())) {
						tmpPricingUnderlying = (PricingUnderlying) underlyingprices.get(tmpStamm.getU_Isin());
					}
					if (underlyingpricesIndex.containsKey(tmpStamm.getU_Isin())) {
						tmpPricingUnderlying = (PricingUnderlying) underlyingpricesIndex.get(tmpStamm.getU_Isin());
					}
					if (hc.containsKey(tmpStamm.getU_Isin())) {
						tmpPricingUnderlying = (PricingUnderlying) hc.get(tmpStamm.getU_Isin());
						underlyingIsCurrency = true;
					}

					if (tmpPricingUnderlying != null) {
						// if(tmpobj.getAsk().compareTo(BigDecimal.ZERO) == 1 &&
						// tmpPricingUnderlying.getAsk().compareTo(BigDecimal.ZERO) > 0) {

						if (tmpobj.getAsk().compareTo(BigDecimal.ZERO) == 1) {

							boolean calc = true;
							BigDecimal nullvalue = new BigDecimal("0.000");
							BigDecimal end = null;
							BigDecimal cap = new BigDecimal(tmpStamm.getCap(), MathContext.DECIMAL64);
							BigDecimal tmpUnd = null;
							boolean curcalcback = false;
							Pricing cur = null;
							BigDecimal tmpStammAsk = null;
							BigDecimal tmpStammBid = null;

							tmpStammAsk = tmpobj.getAsk();
							tmpStammBid = tmpobj.getBid();

							tmpStamm.setAsk(tmpobj.getAsk());
							tmpStamm.setBid(tmpobj.getBid());
							tmpStamm.setMid((tmpobj.getAsk().add(tmpobj.getBid())).divide(new BigDecimal("2.00"), 4,
									RoundingMode.HALF_UP));
							tmpUnd = tmpPricingUnderlying.getAsk();
							// tmpStamm.setSpread(tmpobj.getAsk().subtract(tmpobj.getBid()));

							if (!tmpStamm.getWaehrung().equals("EUR")) {

								cur = (Pricing) currencies.get(tmpStamm.getWaehrung());
								tmpStamm.setCurfaktor(cur.getAsk());

								if (!tmpStamm.getQuanto()) {

									tmpobj.setAsk(tmpobj.ask.multiply(cur.getAsk()));
									tmpobj.setBid(tmpobj.bid.multiply(cur.getAsk()));
									tmpStamm.setAskCur(tmpStamm.getCurfaktor().multiply(tmpStamm.getAsk()));
									tmpStamm.setBidCur(tmpStamm.getCurfaktor().multiply(tmpStamm.getBid()));
									tmpStamm.setMidCur(tmpStamm.getCurfaktor().multiply(tmpStamm.getMid()));
									curcalcback = true;

								}

								if (!underlyingIsCurrency) {
									tmpStamm.setUnderlyingprice(tmpPricingUnderlying.getAsk().multiply(cur.getAsk()));
								} else {
									tmpStamm.setUnderlyingprice(tmpPricingUnderlying.getAsk());
								}

							} else {
								tmpStamm.setUnderlyingprice(tmpPricingUnderlying.getAsk());
							}

							// ----------------------------------
							// int typ, BigDecimal uprice, BigDecimal bv, BigDecimal floor,BigDecimal cap,
							// BigDecimal barriere

							ShortSideCalc h = Utils.helperCalcSide(tmpStamm.getTyp(),
									// tmpPricingUnderlying.getAsk(),
									tmpStamm.getUnderlyingprice(), tmpStamm.getBv(), tmpStamm.getFloor(),
									new BigDecimal(tmpStamm.getCap()), tmpStamm.getBarriere());

							end = h.getEnd();
							calc = h.isCalc();

							tmpStamm.setDerivativetime(tmpobj.getTimestamp());
							tmpStamm.setUnderlyingtime(tmpPricingUnderlying.getTimestamp());
							tmpStamm.setSize(tmpobj.getAsksize());
							tmpStamm.setRestdays(tmpStamm.getRestdays());
							tmpStamm.setMeasureNullor0(false);

							boolean nullOr0 = true;

							if (calc) {
								if (end != null) {

									if (end.compareTo(nullvalue) > 0 && tmpobj.bid.compareTo(nullvalue) > 0
											&& tmpobj.ask.compareTo(nullvalue) > 0) {
										tmpStamm.setMid((tmpobj.bid.add(tmpobj.ask)).divide(new BigDecimal("2.00"), 4,
												RoundingMode.HALF_UP));
									}

									if (end.compareTo(nullvalue) > 0 && tmpobj.ask.compareTo(nullvalue) > 0) {

										tmpStamm.setSide(
												MeasureCalculation.calculateRateOptimizedBigDecimal(end, tmpobj.ask));
										tmpStamm.setSideAnno(MeasureCalculation.calculateAnnualeRateOriginal(
												tmpStamm.getSide().doubleValue(), (int) tmpStamm.getRestdays() + 8));

										if (tmpStamm.getTyp() == 31) {
											tmpStamm.setMaxRendite(tmpStamm.getSide());
											tmpStamm.setMaxRenditepa(tmpStamm.getSideAnno());

										}

										nullOr0 = false;

									}
									if (end.compareTo(nullvalue) > 0 && tmpobj.bid.compareTo(nullvalue) > 0) {
										tmpStamm.setSideBid(
												MeasureCalculation.calculateRateOptimizedBigDecimal(end, tmpobj.bid));
										tmpStamm.setSideAnnoBid(MeasureCalculation.calculateAnnualeRateOriginal(
												tmpStamm.getSideBid().doubleValue(), (int) tmpStamm.getRestdays() + 8));

										if (tmpStamm.getTyp() == 31) {
											tmpStamm.setMaxRenditeBid(tmpStamm.getSideBid());
											tmpStamm.setMaxRenditepaBid(tmpStamm.getSideAnnoBid());

										}
									}
									if (end.compareTo(nullvalue) > 0 && tmpobj.bid.compareTo(nullvalue) > 0
											&& tmpobj.ask.compareTo(nullvalue) > 0) {
										tmpStamm.setSideMid(MeasureCalculation.calculateRateOptimizedBigDecimal(end,
												(tmpobj.bid.add(tmpobj.ask)).divide(new BigDecimal("2.00"), 4,
														RoundingMode.HALF_UP)));
										tmpStamm.setSideAnnoMid(MeasureCalculation.calculateAnnualeRateOriginal(
												tmpStamm.getSideMid().doubleValue(), (int) tmpStamm.getRestdays() + 8));

										if (tmpStamm.getTyp() == 31) {
											tmpStamm.setMaxRenditeMid(tmpStamm.getSideMid());
											tmpStamm.setMaxRenditepaMid(tmpStamm.getSideAnnoMid());

										}
									}
								}
							} else {
								tmpStamm.setSideAnno(-100.00);
							}

							if (nullOr0 && calc) {
								tmpStamm.setMeasureNullor0(true);
							}

							tmpStamm.setAsk(tmpStammAsk);
							tmpStamm.setBid(tmpStammBid);

							resultlist.add(tmpStamm);
						}
					}

				}

			});

		} catch (Exception e) {
			System.out.println("Error in calcSide:" + e.toString());
			System.exit(0);
		}

		return resultlist;
	}
	
	public static ShortSideCalc helperCalcSide(int typ, BigDecimal uprice, BigDecimal bv,
			BigDecimal floor, BigDecimal cap, BigDecimal barriere) {
		boolean calc = true;
		BigDecimal end = null;
		BigDecimal anno = null;

		// tmpStamm.getTyp() = typ
		// tmpStamm.getUnderlyingprice() = uprice
		// tmpStamm.getFloor() = floor
		// tmpStamm.getBv() = bv
		try {
			if (typ == 7) {
				if (uprice.compareTo(floor) > 0) {
					if (uprice.compareTo(cap) > 0) {
						end = cap.subtract(floor).multiply(bv);
					} else {
						end = uprice.subtract(floor).multiply(bv);
					}
				} else {
					anno = new BigDecimal(0.00);
					end = new BigDecimal(0.00);
					calc = false;
				}

			}

			if (typ == 8) {

				if (uprice.compareTo(floor) < 0) {
					if (uprice.compareTo(cap) < 0) {
						end = floor.subtract(cap).multiply(bv);
					} else {
						end = floor.subtract(uprice).multiply(bv);
					}
				} else {
					anno = new BigDecimal(0.00);
					end = new BigDecimal(0.00);
					calc = false;
				}

			}

			if (typ == 1) {
				if (uprice.compareTo(cap) > 0) {
					end = cap.multiply(bv);
				} else {
					end = uprice.multiply(bv);
				}
			}

			if (typ == 2) {

				if (uprice.compareTo(barriere) > 0) {
					end = cap.multiply(bv);
				}
			}

			if (typ == 4) {
				if (cap.compareTo(uprice) > 0) {
					end = cap.multiply(bv);
				} else {
					end = uprice.multiply(bv);
				}
			}

			if (typ == 31) {
				end = cap.multiply(bv);
			}
		} catch (Exception e) {
			System.out.println("typcalc wrong" + 88888);
			System.exit(0);
		}

		return new ShortSideCalc(end, anno, calc);
	}
	
}
