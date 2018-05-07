package com.srt.imuirefactor.business.im.mock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 朱晓龙 on 2018/4/23 20:08.
 */

public class MockRespone {
    private static final String MOCK_JSON = "{\"data\":[" +
           "{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/Fh6xqOXT0W33g1iZJ2gz3FxA15V-\",\"w\":4000,\"h\":3000},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/Fg1ZarsR0ScODH7byUiXf793YkS-\",\"w\":3000,\"h\":4000},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/luSvBCj6KizqqVkP3mey8Xl_S2Ep\",\"w\":5312,\"h\":2988},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/Fom2EDa6wVkeSxiJG037v4JD96Mf\",\"w\":1080,\"h\":1920},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FjvBONxA1qNHehgWqAcRqEF7oS3F\",\"w\":750,\"h\":1334},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FjbwoxvfViWzwEL6HQHq1WdHfXhS\",\"w\":4128,\"h\":3096},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FlxVrNX7ph98YuUsWazO-QMrOOiq\",\"w\":810,\"h\":1777},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FuTBboM82pH4WBONfywYRiyDbdOE\",\"w\":1548,\"h\":2064},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FvKsl3cvOveNZFrc5yOwmZEM08A9\",\"w\":300,\"h\":300},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/Fj5Vuv-cglddajlyo-dXi48t8fNa\",\"w\":4160,\"h\":3120},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FkwQqr3ztnR1J1IL36lhp0jtQD7L\",\"w\":3120,\"h\":4160},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FojxmQExuYcr7QA44zj1JksZWMiN\",\"w\":1080,\"h\":720},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/Fu5lu0rHAYhyCBBKOkqRt0BuyQjz\",\"w\":150,\"h\":150},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/Fg8tAwWnk3PQJm5WXChYq9OEcg2S\",\"w\":400,\"h\":400},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/Ft7P2rrBRZClOGc931LAeAz_JvSZ\",\"w\":84,\"h\":84},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FirLimVv6s5l2wQDSIeGKLJvY9St\",\"w\":84,\"h\":84},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FjPJYLOoJ0exQP7S8P-bEgLtUuQb\",\"w\":1080,\"h\":630},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FrT6JZ4dScyqXXmTa-1k8gViDT73\",\"w\":1080,\"h\":336},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/Ft7P2rrBRZClOGc931LAeAz_JvSZ\",\"w\":84,\"h\":84},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FirLimVv6s5l2wQDSIeGKLJvY9St\",\"w\":84,\"h\":84},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FrT6JZ4dScyqXXmTa-1k8gViDT73\",\"w\":1080,\"h\":336},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FirLimVv6s5l2wQDSIeGKLJvY9St\",\"w\":84,\"h\":84},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/Ft7P2rrBRZClOGc931LAeAz_JvSZ\",\"w\":84,\"h\":84},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FvBxfIq13NZR4hKpjzD-nq6hOMLt\",\"w\":1125,\"h\":2436},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FuBN_GpF-189BCUPxKmZ-3_WCOsl\",\"w\":750,\"h\":1000},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/Fi28OtMY3-CNSbvicbe-BEnn4AM0\",\"w\":750,\"h\":933},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FssGFdWOyV_3z5P6v3v68vb6KlMX\",\"w\":750,\"h\":1315},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/Fj5WEDXYDopfnkOPjoygjApi7mv6\",\"w\":750,\"h\":562},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FpNH9WOspElGnfBm2TjOKqIePEO4\",\"w\":750,\"h\":1332},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FlKIKpHGKDC0b5360khKde8ibYNy\",\"w\":810,\"h\":6742},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FlEVfLSz7ogaqQ7GLsK7jRudzbBE\",\"w\":1440,\"h\":2560},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FjLGF0eoU0ZOJDOFepnqKOyCChRy\",\"w\":290,\"h\":290},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FnlqFVc2jvLGY-VWtG7mC5I7bpXD\",\"w\":3264,\"h\":2448},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FrkArD5yodhdwZHD7_037xtxjD7o\",\"w\":428,\"h\":300},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FozMbbh6-OM7jn25qh-vgr22iVyF\",\"w\":810,\"h\":1863},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/FpVCKml3BNaOAqMXuhuwz3E-RuxH\",\"w\":640,\"h\":1136},\n"+
"{\"url\":\"http://p2xuvkfak.bkt.clouddn.com/Fl89S9JLJhSdDN4wMGmaV2yoz-K7\",\"w\":113,\"h\":113}]}";



    public static ArrayList<MockImageDataBean> getMockResponeData()  {
        JSONObject jsonObject = null;
        ArrayList<MockImageDataBean> resultList=new ArrayList<>();
        try {
            jsonObject = new JSONObject(MOCK_JSON);

            JSONArray jsonArray=jsonObject.getJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object=jsonArray.getJSONObject(i);
            String url=object.getString("url");
            int w=object.getInt("w");
            int h=object.getInt("h");
            resultList.add(new MockImageDataBean(url,w,h));
        }
        } catch (JSONException e) {
            return null;
        }
        return resultList;
    }


    public static class MockImageDataBean{
        /**
         * url :  http://p2xuvkfak.bkt.clouddn.com/Fh6xqOXT0W33g1iZJ2gz3FxA15V-
         * w : 4000
         * h : 3000
         */

        private String url;
        private int w;
        private int h;

        public MockImageDataBean(String url, int w, int h) {
            this.url = url;
            this.w = w;
            this.h = h;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }
    }

}
