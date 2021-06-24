package com.ineptech.magicmirror.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.lang.Boolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ineptech.magicmirror.MainApplication;
import com.ineptech.magicmirror.R;
import com.ineptech.magicmirror.Utils;

///////////////////////////////////////////////
public class TransitModule extends Module{

	ImageSpan isBus;
	int timeToNextBus;

	public TransitModule() {
		super("Mass Transit");
		desc = "Shows the next arrival time for buses and trains.  To add a transit item:"
				+ "\n * First enter a name (\"Bus\" will be replaced with a bus icon, and "
				+ "\"Train\" with a train icon)\n * Next, find out what URL you should use "
				+ "to get the next arrival time of your bus or train (consult the \"Developers\" "
				+ "section of your transit service website) and paste in the URL field"
				+ "\n * Finally, enter a regular expression (regex) in the last field that extracts "
				+ "the arrival time (in epoch time) from the transit service API response.  "
				+ "\n This can be tricky to configure - see github.com/ineptech/mirror for more info.";
		defaultTextSize = 72;
		sampleString = "Train: 17m  Bus: 23m";
		loadConfig();

	}

	private void loadConfig() {

	}

	public ArrayList<Integer> weekdaySchedule = new ArrayList<Integer>();
	public ArrayList<Integer> weekendSchedule = new ArrayList<Integer>();

	public void setSchedule(){

		Calendar now = Calendar.getInstance();
		int dayOfWeek = now.get(Calendar.DAY_OF_WEEK);
		int dayOfMonth = now.get(Calendar.DAY_OF_MONTH);
		int month = now.get(Calendar.MONTH);
		int year = now.get(Calendar.YEAR);

		boolean isWeekday = ((dayOfWeek >= Calendar.MONDAY) && (dayOfWeek <= Calendar.FRIDAY ));

		//New Years Day, Christmas, Independence Day
		boolean isHoliday = (dayOfMonth==1 && month==1) || (dayOfMonth==25 && month==12) || (dayOfMonth==4 && month==7);

		//rotating holidays- Thanksgiving and Memorial Day
		boolean isRotHoliday = false;

		if (year == 2017) {
			isRotHoliday = (dayOfMonth==29 && month==5) || (dayOfMonth==23 && month==11);
		}
		if (year == 2018) {
			isRotHoliday = (dayOfMonth==28 && month==5) || (dayOfMonth==22 && month==11);
		}
		if (year == 2019) {
			isRotHoliday = (dayOfMonth==27 && month==5) || (dayOfMonth==28 && month==11);
		}
		if (year == 2020) {
			isRotHoliday = (dayOfMonth==25 && month==5) || (dayOfMonth==26 && month==11);
		}
		if (year == 2021) {
			isRotHoliday = (dayOfMonth==31 && month==5) || (dayOfMonth==25 && month==11);
		}
		if (year == 2022) {
			isRotHoliday = (dayOfMonth==30 && month==5) || (dayOfMonth==24 && month==11);
		}


		//holidays
		if (isHoliday || isRotHoliday)
			timeToNextBus=-1;

			//weekday schedule
		else if (isWeekday) {

			//spring 2019
			//spring 2020
			//rt 10, seneca at commons
			weekdaySchedule.add( 710);
			weekdaySchedule.add( 720);
			weekdaySchedule.add( 730);
			weekdaySchedule.add( 740);
			weekdaySchedule.add( 750);
			weekdaySchedule.add( 800);
			weekdaySchedule.add( 807);
			weekdaySchedule.add( 814);
			weekdaySchedule.add( 821);
			weekdaySchedule.add( 827);
			weekdaySchedule.add( 833);
			weekdaySchedule.add( 839);
			weekdaySchedule.add( 844);
			weekdaySchedule.add( 849);
			weekdaySchedule.add( 854);
			weekdaySchedule.add( 900);
			weekdaySchedule.add( 905);
			weekdaySchedule.add( 910);
			weekdaySchedule.add( 915);
			weekdaySchedule.add( 921);
			weekdaySchedule.add( 926);
			weekdaySchedule.add( 931);
			weekdaySchedule.add( 936);
			weekdaySchedule.add( 941);
			weekdaySchedule.add( 946);
			weekdaySchedule.add( 951);
			weekdaySchedule.add( 956);
			weekdaySchedule.add(1002);
			weekdaySchedule.add(1008);
			weekdaySchedule.add(1014);
			weekdaySchedule.add(1021);
			weekdaySchedule.add(1028);
			weekdaySchedule.add(1036);
			weekdaySchedule.add(1044);
			weekdaySchedule.add(1052);
			weekdaySchedule.add(1100);
			weekdaySchedule.add(1108);
			weekdaySchedule.add(1116);
			weekdaySchedule.add(1124);
			weekdaySchedule.add(1132);
			weekdaySchedule.add(1140);
			weekdaySchedule.add(1148);
			weekdaySchedule.add(1156);
			weekdaySchedule.add(1204);
			weekdaySchedule.add(1212);
			weekdaySchedule.add(1220);
			weekdaySchedule.add(1228);
			weekdaySchedule.add(1236);
			weekdaySchedule.add(1244);
			weekdaySchedule.add(1252);
			weekdaySchedule.add(1300);
			weekdaySchedule.add(1310);
			weekdaySchedule.add(1322);
			weekdaySchedule.add(1334);
			weekdaySchedule.add(1346);
			weekdaySchedule.add(1358);
			weekdaySchedule.add(1410);
			weekdaySchedule.add(1422);
			weekdaySchedule.add(1434);
			weekdaySchedule.add(1446);
			weekdaySchedule.add(1458);
			weekdaySchedule.add(1510);
			weekdaySchedule.add(1522);
			weekdaySchedule.add(1534);
			weekdaySchedule.add(1546);
			weekdaySchedule.add(1558);
			weekdaySchedule.add(1610);
			weekdaySchedule.add(1622);
			weekdaySchedule.add(1634);
			weekdaySchedule.add(1646);
			weekdaySchedule.add(1658);
			weekdaySchedule.add(1710);
			weekdaySchedule.add(1722);
			weekdaySchedule.add(1734);
			weekdaySchedule.add(1746);
			weekdaySchedule.add(1758);
			weekdaySchedule.add(1812);
			weekdaySchedule.add(1836);
			weekdaySchedule.add(1900);
			weekdaySchedule.add(1924);



			/*
			//facebook shuttle
			weekdaySchedule.add(700);
			weekdaySchedule.add(730);
			weekdaySchedule.add(740);
			weekdaySchedule.add(755);
			weekdaySchedule.add(805);
			weekdaySchedule.add(820);
			weekdaySchedule.add(830);
			weekdaySchedule.add(840);
			weekdaySchedule.add(850);
			weekdaySchedule.add(905);
			weekdaySchedule.add(915);
			weekdaySchedule.add(935);
			weekdaySchedule.add(955);

			weekdaySchedule.add(1638);
			weekdaySchedule.add(1718);
			weekdaySchedule.add(1725);
			weekdaySchedule.add(1802);
			weekdaySchedule.add(1812);
			weekdaySchedule.add(1829);
			weekdaySchedule.add(1845);
			weekdaySchedule.add(1856);
			weekdaySchedule.add(1906);
			weekdaySchedule.add(1926);
			weekdaySchedule.add(1953);
			weekdaySchedule.add(2016);
			weekdaySchedule.add(2058);
			*/

/*
			//fall 2018
			//summer 2017
			//rt 10, seneca at commons
			weekdaySchedule.add(712);
			weekdaySchedule.add(736);
			weekdaySchedule.add(748);
			weekdaySchedule.add(800);
			weekdaySchedule.add(806);
			weekdaySchedule.add(812);
			weekdaySchedule.add(818);
			weekdaySchedule.add(824);
			weekdaySchedule.add(830);
			weekdaySchedule.add(836);
			weekdaySchedule.add(842);
			weekdaySchedule.add(848);
			weekdaySchedule.add(854);
			weekdaySchedule.add(900);
			weekdaySchedule.add(906);
			weekdaySchedule.add(912);
			weekdaySchedule.add(918);
			weekdaySchedule.add(924);
			weekdaySchedule.add(930);
			weekdaySchedule.add(936);
			weekdaySchedule.add(942);
			weekdaySchedule.add(948);
			weekdaySchedule.add(954);
			weekdaySchedule.add(1000);
			weekdaySchedule.add(1008);
			weekdaySchedule.add(1016);
			weekdaySchedule.add(1024);
			weekdaySchedule.add(1032);
			weekdaySchedule.add(1040);
			weekdaySchedule.add(1048);
			weekdaySchedule.add(1056);
			weekdaySchedule.add(1104);
			weekdaySchedule.add(1112);
			weekdaySchedule.add(1120);
			weekdaySchedule.add(1128);
			weekdaySchedule.add(1136);
			weekdaySchedule.add(1144);
			weekdaySchedule.add(1152);
			weekdaySchedule.add(1200);
			weekdaySchedule.add(1208);
			weekdaySchedule.add(1216);
			weekdaySchedule.add(1224);
			weekdaySchedule.add(1232);
			weekdaySchedule.add(1240);
			weekdaySchedule.add(1248);
			weekdaySchedule.add(1256);
			weekdaySchedule.add(1304);
			weekdaySchedule.add(1316);
			weekdaySchedule.add(1328);
			weekdaySchedule.add(1340);
			weekdaySchedule.add(1352);
			weekdaySchedule.add(1404);
			weekdaySchedule.add(1416);
			weekdaySchedule.add(1428);
			weekdaySchedule.add(1440);
			weekdaySchedule.add(1452);
			weekdaySchedule.add(1504);
			weekdaySchedule.add(1516);
			weekdaySchedule.add(1528);
			weekdaySchedule.add(1540);
			weekdaySchedule.add(1552);
			weekdaySchedule.add(1604);
			weekdaySchedule.add(1616);
			weekdaySchedule.add(1628);
			weekdaySchedule.add(1640);
			weekdaySchedule.add(1652);
			weekdaySchedule.add(1704);
			weekdaySchedule.add(1716);
			weekdaySchedule.add(1728);
			weekdaySchedule.add(1740);
			weekdaySchedule.add(1752);
			weekdaySchedule.add(1804);
			weekdaySchedule.add(1816);
			weekdaySchedule.add(1840);
			weekdaySchedule.add(1904);
			weekdaySchedule.add(1928);
			weekdaySchedule.add(1952);

			//spring 2019 trash
			weekdaySchedule.add( 805);
			weekdaySchedule.add( 810);
			weekdaySchedule.add( 815);
			weekdaySchedule.add( 820);
			weekdaySchedule.add( 825);
			weekdaySchedule.add( 830);
			weekdaySchedule.add( 835);
			weekdaySchedule.add( 840);
			weekdaySchedule.add( 845);
			weekdaySchedule.add( 850);
			weekdaySchedule.add( 855);
			weekdaySchedule.add( 900);
			weekdaySchedule.add( 905);
			weekdaySchedule.add( 910);
			weekdaySchedule.add( 915);
			weekdaySchedule.add( 920);
			weekdaySchedule.add( 925);
			weekdaySchedule.add( 930);
			weekdaySchedule.add( 935);
			weekdaySchedule.add( 940);
			weekdaySchedule.add( 945);
			weekdaySchedule.add( 950);
			weekdaySchedule.add( 955);
			weekdaySchedule.add(1000);
			weekdaySchedule.add(1008);
			weekdaySchedule.add(1016);
			weekdaySchedule.add(1024);
			weekdaySchedule.add(1032);
			weekdaySchedule.add(1040);
			weekdaySchedule.add(1048);
			weekdaySchedule.add(1056);
			weekdaySchedule.add(1104);
			weekdaySchedule.add(1112);
			weekdaySchedule.add(1120);
			weekdaySchedule.add(1128);
			weekdaySchedule.add(1136);
			weekdaySchedule.add(1144);
			weekdaySchedule.add(1152);
			weekdaySchedule.add(1200);
			weekdaySchedule.add(1208);
			weekdaySchedule.add(1216);
			weekdaySchedule.add(1224);
			weekdaySchedule.add(1232);
			weekdaySchedule.add(1240);
			weekdaySchedule.add(1248);
			weekdaySchedule.add(1256);
			weekdaySchedule.add(1308);
			weekdaySchedule.add(1320);
			weekdaySchedule.add(1332);
			weekdaySchedule.add(1344);
			weekdaySchedule.add(1356);
			weekdaySchedule.add(1408);
			weekdaySchedule.add(1420);
			weekdaySchedule.add(1432);
			weekdaySchedule.add(1444);
			weekdaySchedule.add(1456);
			weekdaySchedule.add(1508);
			weekdaySchedule.add(1520);
			weekdaySchedule.add(1532);
			weekdaySchedule.add(1544);
			weekdaySchedule.add(1556);
			weekdaySchedule.add(1608);
			weekdaySchedule.add(1620);
			weekdaySchedule.add(1632);
			weekdaySchedule.add(1644);
			weekdaySchedule.add(1656);
			weekdaySchedule.add(1708);
			weekdaySchedule.add(1720);
			weekdaySchedule.add(1732);
			weekdaySchedule.add(1744);
			weekdaySchedule.add(1756);
			weekdaySchedule.add(1808);
			weekdaySchedule.add(1832);
			weekdaySchedule.add(1856);
			weekdaySchedule.add(1920);
			weekdaySchedule.add(1944);



			/*
			//spring 2017 schedule
            //fall 2017 schedule
			//Schedule for Rt 10, Stop Seneca at Commons
			weekdaySchedule.add( 712);
			weekdaySchedule.add( 724);
			weekdaySchedule.add( 736);
			weekdaySchedule.add( 748);
			weekdaySchedule.add( 800);
			weekdaySchedule.add( 806);
			weekdaySchedule.add( 812);
			weekdaySchedule.add( 818);
			weekdaySchedule.add( 824);
			weekdaySchedule.add( 830);
			weekdaySchedule.add( 836);
			weekdaySchedule.add( 842);
			weekdaySchedule.add( 848);
			weekdaySchedule.add( 854);
			weekdaySchedule.add( 900);
			weekdaySchedule.add( 906);
			weekdaySchedule.add( 912);
			weekdaySchedule.add( 918);
			weekdaySchedule.add( 924);
			weekdaySchedule.add( 930);
			weekdaySchedule.add( 935);
			weekdaySchedule.add( 940);
			weekdaySchedule.add( 945);
			weekdaySchedule.add( 950);
			weekdaySchedule.add( 956);
			weekdaySchedule.add(1002);
			weekdaySchedule.add(1010);
			weekdaySchedule.add(1018);
			weekdaySchedule.add(1026);
			weekdaySchedule.add(1034);
			weekdaySchedule.add(1042);
			weekdaySchedule.add(1050);
			weekdaySchedule.add(1058);
			weekdaySchedule.add(1106);
			weekdaySchedule.add(1114);
			weekdaySchedule.add(1122);
			weekdaySchedule.add(1130);
			weekdaySchedule.add(1138);
			weekdaySchedule.add(1146);
			weekdaySchedule.add(1154);
			weekdaySchedule.add(1202);
			weekdaySchedule.add(1210);
			weekdaySchedule.add(1218);
			weekdaySchedule.add(1226);
			weekdaySchedule.add(1234);
			weekdaySchedule.add(1242);
			weekdaySchedule.add(1250);
			weekdaySchedule.add(1258);
			weekdaySchedule.add(1310);
			weekdaySchedule.add(1322);
			weekdaySchedule.add(1334);
			weekdaySchedule.add(1346);
			weekdaySchedule.add(1358);
			weekdaySchedule.add(1410);
			weekdaySchedule.add(1422);
			weekdaySchedule.add(1434);
			weekdaySchedule.add(1446);
			weekdaySchedule.add(1458);
			weekdaySchedule.add(1510);
			weekdaySchedule.add(1522);
			weekdaySchedule.add(1534);
			weekdaySchedule.add(1546);
			weekdaySchedule.add(1558);
			weekdaySchedule.add(1610);
			weekdaySchedule.add(1622);
			weekdaySchedule.add(1634);
			weekdaySchedule.add(1646);
			weekdaySchedule.add(1658);
			weekdaySchedule.add(1710);
			weekdaySchedule.add(1722);
			weekdaySchedule.add(1734);
			weekdaySchedule.add(1746);
			weekdaySchedule.add(1758);
			weekdaySchedule.add(1822);
			weekdaySchedule.add(1846);
			weekdaySchedule.add(1910);
			weekdaySchedule.add(1934);
			weekdaySchedule.add(1958);
*/
			/*
			//spring 2018 schedule
			//Schedule for Rt 10, Stop Seneca at Commons
			weekdaySchedule.add( 711);
			weekdaySchedule.add( 721);
			weekdaySchedule.add( 731);
			weekdaySchedule.add( 741);
			weekdaySchedule.add( 751);
			weekdaySchedule.add( 801);
			weekdaySchedule.add( 811);
			weekdaySchedule.add( 816);
			weekdaySchedule.add( 821);
			weekdaySchedule.add( 826);
			weekdaySchedule.add( 831);
			weekdaySchedule.add( 836);
			weekdaySchedule.add( 841);
			weekdaySchedule.add( 846);
			weekdaySchedule.add( 851);
			weekdaySchedule.add( 856);
			weekdaySchedule.add( 901);
			weekdaySchedule.add( 906);
			weekdaySchedule.add( 911);
			weekdaySchedule.add( 916);
			weekdaySchedule.add( 926);
			weekdaySchedule.add( 934);
			weekdaySchedule.add( 939);
			weekdaySchedule.add( 944);
			weekdaySchedule.add( 949);
			weekdaySchedule.add( 954);
			weekdaySchedule.add( 959);
			weekdaySchedule.add(1004);
			weekdaySchedule.add(1009);
			weekdaySchedule.add(1014);
			weekdaySchedule.add(1019);
			weekdaySchedule.add(1028);
			weekdaySchedule.add(1036);
			weekdaySchedule.add(1044);
			weekdaySchedule.add(1052);
			weekdaySchedule.add(1100);
			weekdaySchedule.add(1108);
			weekdaySchedule.add(1124);
			weekdaySchedule.add(1132);
			weekdaySchedule.add(1140);
			weekdaySchedule.add(1148);
			weekdaySchedule.add(1156);
			weekdaySchedule.add(1204);
			weekdaySchedule.add(1212);
			weekdaySchedule.add(1220);
			weekdaySchedule.add(1228);
			weekdaySchedule.add(1236);
			weekdaySchedule.add(1244);
			weekdaySchedule.add(1252);
			weekdaySchedule.add(1300);
			weekdaySchedule.add(1308);
			weekdaySchedule.add(1321);
			weekdaySchedule.add(1331);
			weekdaySchedule.add(1341);
			weekdaySchedule.add(1351);
			weekdaySchedule.add(1401);
			weekdaySchedule.add(1411);
			weekdaySchedule.add(1421);
			weekdaySchedule.add(1431);
			weekdaySchedule.add(1441);
			weekdaySchedule.add(1451);
			weekdaySchedule.add(1501);
			weekdaySchedule.add(1511);
			weekdaySchedule.add(1521);
			weekdaySchedule.add(1531);
			weekdaySchedule.add(1541);
			weekdaySchedule.add(1551);
			weekdaySchedule.add(1601);
			weekdaySchedule.add(1611);
			weekdaySchedule.add(1613);
			weekdaySchedule.add(1623);
			weekdaySchedule.add(1633);
			weekdaySchedule.add(1643);
			weekdaySchedule.add(1653);
			weekdaySchedule.add(1703);
			weekdaySchedule.add(1713);
			weekdaySchedule.add(1723);
			weekdaySchedule.add(1733);
			weekdaySchedule.add(1740);
			weekdaySchedule.add(1750);
			weekdaySchedule.add(1800);
			weekdaySchedule.add(1805);
			weekdaySchedule.add(1810);
			weekdaySchedule.add(1826);
			weekdaySchedule.add(1847);
			weekdaySchedule.add(1908);
			weekdaySchedule.add(1929);
			weekdaySchedule.add(1950);
			*/


			//when is the next bus?
			Date today = new Date();

			now.setTime(today);
			int hours = now.get(Calendar.HOUR_OF_DAY);
			int minutes = now.get(Calendar.MINUTE);

			int currentTime = hours*100 + minutes;

			int i = 0;
			timeToNextBus=0;

			//find time to next bus
			while (timeToNextBus==0) {

				int lastTime = weekdaySchedule.get(weekdaySchedule.size() - 1);
				int firstTime = weekdaySchedule.get(0);

				//is the current time before the bus stops running?
				if (currentTime < lastTime) {
					//is the current time reasonable for looking for a bus?
					if(currentTime > (firstTime - 15)) {

						//search the schedule
						if (currentTime >= weekdaySchedule.get(i))
							i++;
						else {
							//comparing hour for now vs. next bus time
							//if hours are different, subtraction is no longer simple
							int hourSchedule = weekdaySchedule.get(i) - weekdaySchedule.get(i) % 100;
							int hourNow = hours * 100;

							if (hourSchedule == hourNow) {
								timeToNextBus = weekdaySchedule.get(i) - currentTime;
							} else {
								timeToNextBus = weekdaySchedule.get(i) - currentTime - 40;
							}

						}
					}
					else timeToNextBus = -1;
				}

				else timeToNextBus=-1;
			}


		}

		//weekend schedule
		else
		{

			//if bus doesn't run on weekend
			//timeToNextBus = -1;


			if (dayOfWeek == Calendar.SATURDAY){

				//fall 2018 - green
				//spring 2019
				weekendSchedule.add( 700);
				weekendSchedule.add( 730);
				weekendSchedule.add( 800);
				weekendSchedule.add( 830);
				weekendSchedule.add( 900);
				weekendSchedule.add( 930);
				weekendSchedule.add(1000);
				weekendSchedule.add(1015);
				weekendSchedule.add(1030);
				weekendSchedule.add(1045);
				weekendSchedule.add(1100);
				weekendSchedule.add(1115);
				weekendSchedule.add(1130);
				weekendSchedule.add(1145);
				weekendSchedule.add(1200);
				weekendSchedule.add(1215);
				weekendSchedule.add(1230);
				weekendSchedule.add(1245);
				weekendSchedule.add(1300);
				weekendSchedule.add(1315);
				weekendSchedule.add(1330);
				weekendSchedule.add(1345);
				weekendSchedule.add(1400);
				weekendSchedule.add(1415);
				weekendSchedule.add(1430);
				weekendSchedule.add(1445);
				weekendSchedule.add(1500);
				weekendSchedule.add(1515);
				weekendSchedule.add(1530);
				weekendSchedule.add(1545);
				weekendSchedule.add(1600);
				weekendSchedule.add(1615);
				weekendSchedule.add(1630);
				weekendSchedule.add(1645);
				weekendSchedule.add(1700);
				weekendSchedule.add(1715);
				weekendSchedule.add(1730);
				weekendSchedule.add(1745);
				weekendSchedule.add(1800);
				weekendSchedule.add(1830);
				weekendSchedule.add(1845);
				weekendSchedule.add(1900);
				weekendSchedule.add(1930);
				weekendSchedule.add(2000);
				weekendSchedule.add(2030);
				weekendSchedule.add(2100);
				weekendSchedule.add(2130);
				weekendSchedule.add(2200);
				weekendSchedule.add(2230);
				weekendSchedule.add(2215);
				weekendSchedule.add(2300);
				weekendSchedule.add(2325);

			/*
			//fall 2017 - green
				weekendSchedule.add( 700);
				weekendSchedule.add( 730);
				weekendSchedule.add( 800);
				weekendSchedule.add( 830);
				weekendSchedule.add( 900);
				weekendSchedule.add( 930);
				weekendSchedule.add(1000);
				weekendSchedule.add(1030);
				weekendSchedule.add(1045);
				weekendSchedule.add(1100);
				weekendSchedule.add(1115);
				weekendSchedule.add(1130);
				weekendSchedule.add(1145);
				weekendSchedule.add(1200);
				weekendSchedule.add(1215);
				weekendSchedule.add(1230);
				weekendSchedule.add(1245);
				weekendSchedule.add(1300);
				weekendSchedule.add(1315);
				weekendSchedule.add(1330);
				weekendSchedule.add(1345);
				weekendSchedule.add(1400);
				weekendSchedule.add(1415);
				weekendSchedule.add(1430);
				weekendSchedule.add(1445);
				weekendSchedule.add(1500);
				weekendSchedule.add(1515);
				weekendSchedule.add(1530);
				weekendSchedule.add(1545);
				weekendSchedule.add(1600);
				weekendSchedule.add(1615);
				weekendSchedule.add(1630);
				weekendSchedule.add(1645);
				weekendSchedule.add(1700);
				weekendSchedule.add(1715);
				weekendSchedule.add(1730);
				weekendSchedule.add(1745);
				weekendSchedule.add(1800);
				weekendSchedule.add(1830);
				weekendSchedule.add(1900);
				weekendSchedule.add(1930);
				weekendSchedule.add(2000);
				weekendSchedule.add(2030);
				weekendSchedule.add(2100);
				weekendSchedule.add(2130);
				weekendSchedule.add(2200);
				weekendSchedule.add(2230);
				weekendSchedule.add(2215);
				weekendSchedule.add(2300);
				weekendSchedule.add(2325);



			//summer 2017
			//Schedule for Outbound Rt 30, 70 at Green
				weekendSchedule.add( 700);
				weekendSchedule.add( 730);
				weekendSchedule.add( 800);
				weekendSchedule.add( 830);
				weekendSchedule.add( 900);
				weekendSchedule.add( 930);
				weekendSchedule.add(1000);
				weekendSchedule.add(1030);
				weekendSchedule.add(1100);
				weekendSchedule.add(1130);
				weekendSchedule.add(1200);
				weekendSchedule.add(1230);
				weekendSchedule.add(1300);
				weekendSchedule.add(1330);
				weekendSchedule.add(1400);
				weekendSchedule.add(1430);
				weekendSchedule.add(1500);
				weekendSchedule.add(1530);
				weekendSchedule.add(1600);
				weekendSchedule.add(1630);
				weekendSchedule.add(1700);
				weekendSchedule.add(1730);
				weekendSchedule.add(1800);
				weekendSchedule.add(1830);
				weekendSchedule.add(1900);
				weekendSchedule.add(1930);
				weekendSchedule.add(2000);
				weekendSchedule.add(2030);
				weekendSchedule.add(2100);
				weekendSchedule.add(2130);
				weekendSchedule.add(2200);
				weekendSchedule.add(2230);
				weekendSchedule.add(2300);




			//spring 2018
			//Schedule for Outbound Rt 30, 70 at Green
				weekendSchedule.add( 650);
				weekendSchedule.add( 720);
				weekendSchedule.add( 750);
				weekendSchedule.add( 820);
				weekendSchedule.add( 850);
				weekendSchedule.add( 920);
				weekendSchedule.add( 950);
				weekendSchedule.add(1020);
				weekendSchedule.add(1035);
				weekendSchedule.add(1050);
				weekendSchedule.add(1105);
				weekendSchedule.add(1120);
				weekendSchedule.add(1135);
				weekendSchedule.add(1150);
				weekendSchedule.add(1205);
				weekendSchedule.add(1220);
				weekendSchedule.add(1235);
				weekendSchedule.add(1250);
				weekendSchedule.add(1305);
				weekendSchedule.add(1320);
				weekendSchedule.add(1335);
				weekendSchedule.add(1350);
				weekendSchedule.add(1405);
				weekendSchedule.add(1420);
				weekendSchedule.add(1435);
				weekendSchedule.add(1450);
				weekendSchedule.add(1505);
				weekendSchedule.add(1520);
				weekdaySchedule.add(1535);
				weekendSchedule.add(1550);
				weekendSchedule.add(1605);
				weekendSchedule.add(1620);
				weekendSchedule.add(1635);
				weekendSchedule.add(1650);
				weekendSchedule.add(1705);
				weekendSchedule.add(1720);
				weekendSchedule.add(1735);
				weekendSchedule.add(1750);
				weekendSchedule.add(1820);
				weekendSchedule.add(1850);
				weekendSchedule.add(1920);
				weekendSchedule.add(1950);
				weekendSchedule.add(2020);
				weekendSchedule.add(2050);
				weekendSchedule.add(2120);
				weekendSchedule.add(2150);
				weekendSchedule.add(2220);
				weekendSchedule.add(2250);
				*/

			}

			if (dayOfWeek == Calendar.SUNDAY) {

				//fall 2018
				//spring 2019
				weekendSchedule.add( 700);
				weekendSchedule.add( 800);
				weekendSchedule.add( 900);
				weekendSchedule.add( 930);
				weekendSchedule.add(1000);
				weekendSchedule.add(1015);
				weekendSchedule.add(1030);
				weekendSchedule.add(1045);
				weekendSchedule.add(1100);
				weekendSchedule.add(1115);
				weekendSchedule.add(1130);
				weekendSchedule.add(1145);
				weekendSchedule.add(1200);
				weekendSchedule.add(1215);
				weekendSchedule.add(1230);
				weekendSchedule.add(1245);
				weekendSchedule.add(1300);
				weekendSchedule.add(1315);
				weekendSchedule.add(1330);
				weekendSchedule.add(1345);
				weekendSchedule.add(1400);
				weekendSchedule.add(1415);
				weekendSchedule.add(1430);
				weekendSchedule.add(1445);
				weekendSchedule.add(1500);
				weekendSchedule.add(1515);
				weekendSchedule.add(1530);
				weekendSchedule.add(1545);
				weekendSchedule.add(1600);
				weekendSchedule.add(1615);
				weekendSchedule.add(1630);
				weekendSchedule.add(1645);
				weekendSchedule.add(1700);
				weekendSchedule.add(1715);
				weekendSchedule.add(1730);
				weekendSchedule.add(1800);


				/*
				//fall 2017
				weekendSchedule.add( 700);
				weekendSchedule.add( 800);
				weekendSchedule.add( 900);
				weekendSchedule.add( 930);
				weekendSchedule.add(1000);
				weekendSchedule.add(1030);
				weekendSchedule.add(1045);
				weekendSchedule.add(1100);
				weekendSchedule.add(1115);
				weekendSchedule.add(1130);
				weekendSchedule.add(1145);
				weekendSchedule.add(1200);
				weekendSchedule.add(1215);
				weekendSchedule.add(1230);
				weekendSchedule.add(1245);
				weekendSchedule.add(1300);
				weekendSchedule.add(1315);
				weekendSchedule.add(1330);
				weekendSchedule.add(1345);
				weekendSchedule.add(1400);
				weekendSchedule.add(1415);
				weekendSchedule.add(1430);
				weekendSchedule.add(1445);
				weekendSchedule.add(1500);
				weekendSchedule.add(1515);
				weekendSchedule.add(1530);
				weekendSchedule.add(1545);
				weekendSchedule.add(1600);
				weekendSchedule.add(1615);
				weekendSchedule.add(1630);
				weekendSchedule.add(1645);
				weekendSchedule.add(1700);
				weekendSchedule.add(1715);
				weekendSchedule.add(1730);
				weekendSchedule.add(1745);
				weekendSchedule.add(1800);


				//summer 2017
				weekendSchedule.add( 700);
				weekendSchedule.add( 800);
				weekendSchedule.add( 900);
				weekendSchedule.add( 930);
				weekendSchedule.add(1000);
				weekendSchedule.add(1030);
				weekendSchedule.add(1100);
				weekendSchedule.add(1130);
				weekendSchedule.add(1200);
				weekendSchedule.add(1230);
				weekendSchedule.add(1300);
				weekendSchedule.add(1330);
				weekendSchedule.add(1400);
				weekendSchedule.add(1430);
				weekendSchedule.add(1500);
				weekendSchedule.add(1530);
				weekendSchedule.add(1600);
				weekendSchedule.add(1630);
				weekendSchedule.add(1700);
				weekendSchedule.add(1730);
				weekendSchedule.add(1800);


				//spring 2017
				//Schedule for Outbound Rt 30, 70 at Green
				weekendSchedule.add( 930);
				weekendSchedule.add( 950);
				weekendSchedule.add(1020);
				weekendSchedule.add(1035);
				weekendSchedule.add(1050);
				weekendSchedule.add(1105);
				weekendSchedule.add(1120);
				weekendSchedule.add(1135);
				weekendSchedule.add(1150);
				weekendSchedule.add(1205);
				weekendSchedule.add(1220);
				weekendSchedule.add(1235);
				weekendSchedule.add(1250);
				weekendSchedule.add(1305);
				weekendSchedule.add(1320);
				weekendSchedule.add(1335);
				weekendSchedule.add(1350);
				weekendSchedule.add(1405);
				weekendSchedule.add(1420);
				weekendSchedule.add(1435);
				weekendSchedule.add(1450);
				weekendSchedule.add(1505);
				weekendSchedule.add(1520);
				weekdaySchedule.add(1535);
				weekendSchedule.add(1550);
				weekendSchedule.add(1605);
				weekendSchedule.add(1620);
				weekendSchedule.add(1635);
				weekendSchedule.add(1650);
				weekendSchedule.add(1705);
				weekendSchedule.add(1720);
				weekendSchedule.add(1750);
				*/

			}


			//when is the next bus?
			Date today = new Date();

			now.setTime(today);
			int hours = now.get(Calendar.HOUR_OF_DAY);
			int minutes = now.get(Calendar.MINUTE);

			int currentTime = hours*100 + minutes;

			int i = 0;
			timeToNextBus=0;

			//find time to next bus
			while (timeToNextBus==0) {

				int lastTime = weekendSchedule.get(weekendSchedule.size() - 1);
				int firstTime = weekendSchedule.get(0);

				//is the current time before the bus stops running?
				if (currentTime < lastTime) {
					//is the current time reasonable for looking for a bus?
					if(currentTime > (firstTime - 15)) {

						//search the schedule
						if (currentTime >= weekendSchedule.get(i))
							i++;
						else {
							//comparing hour for now vs. next bus time
							//if hours are different, subtraction is no longer simple
							int hourSchedule = weekendSchedule.get(i) - weekendSchedule.get(i) % 100;
							int hourNow = hours * 100;

							if (hourSchedule == hourNow) {
								timeToNextBus = weekendSchedule.get(i) - currentTime;
							} else {
								timeToNextBus = weekendSchedule.get(i) - currentTime - 40;
							}
						}

					}
					else timeToNextBus=-1;
				}
				else timeToNextBus=-1;
			}


		}

		return;
	}

	public void setIconSizes() {
		Context context = MainApplication.getContext();
		int iconSize = Math.round(tv.getTextSize());
		Drawable iconBus = context.getResources().getDrawable(R.drawable.bus);
		iconBus.setBounds(0,0,iconSize,iconSize);
		isBus = new ImageSpan(iconBus);
	}

	@Override
	public void saveConfig() {
		super.saveConfig();

	}

	@Override
	public void makeConfigLayout() {
		super.makeConfigLayout();

	}

	public void update() {

		setSchedule();
		setIconSizes();

		//next bus only visible if: weekday and more buses scheduled today
		if (timeToNextBus != -1) {

			tv.setVisibility(TextView.VISIBLE);

			SpannableStringBuilder builder = new SpannableStringBuilder();

			String timeToNextBusString = Integer.toString(timeToNextBus);
			String nextBus;

			if (timeToNextBus == 1)
				nextBus = timeToNextBusString + " minute";
			else
				nextBus = timeToNextBusString + " minutes";

			String s = "Bus " + nextBus + "   ";
			SpannableString ss = new SpannableString(s);
			int b = s.indexOf("Bus");
			ss.setSpan(isBus, b, b + 3, 0);

			builder.append(ss);

			tv.setText(builder);

		}

		else {
			tv.setText("");
			tv.setVisibility(TextView.GONE);
		}

	}

}

