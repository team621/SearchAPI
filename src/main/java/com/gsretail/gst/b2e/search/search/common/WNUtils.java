package com.gsretail.gst.b2e.search.search.common;

import com.gsretail.gst.b2e.search.search.model.Search;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * packageName    : com.gsretail.gst.b2e.search.search.common
 * fileName       : WNUtils
 * author         : USER
 * date           : 2022-06-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07        USER       최초 생성
 */
public class WNUtils {
    /*
     *	문자셋 관련 설정
     */
    final static String ENCODE_ORI = "EUC-KR";
    final static String ENCODE_NEW = "UTF-8";

    /**
     * 문자 배열 값을 검색하여 키 값을 리턴
     * @return
     */
    static int findArrayValue(String find, String[] arr) {
        int findKey = -1;
        for (int i = 0; i < arr.length; i++) {
            if (find.equals(arr[i])){
                findKey = i;
                break;
            }
        }
        return findKey;
    }

    /**
     *
     * @param s
     * @param findStr
     * @param replaceStr
     * @return
     */
    public static String replace(String s, String findStr, String replaceStr){
        int   pos;
        int   index = 0;

        while ((pos = s.indexOf(findStr, index)) >= 0) {
            s = s.substring(0, pos) + replaceStr + s.substring(pos + findStr.length());
            index = pos + replaceStr.length();
        }

        return s;
    }

    /**
     *
     * @param s
     * @return
     */
    public static String trimDuplecateSpace(String s){
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(i < s.length()-1) {
                if( c == ' ' && s.charAt(i+1)==' '){
                    continue;
                }
            }
            sb.append(c);
        }
        return sb.toString().trim();
    }

    public static String parseDate(String input, String inFormat, String outFormat) {
        String retStr = "";
        Date date = null;
        SimpleDateFormat formatter = null;
        try {
            date = (new SimpleDateFormat(inFormat)).parse(input.trim());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatter = new SimpleDateFormat(outFormat);
        retStr = formatter.format(date);
        return retStr;
    }

    public static String getCurrentDate() {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat
                ("yyyy.MM.dd", java.util.Locale.KOREA);
        return dateFormat.format(new java.util.Date());
    }

    /**
     *
     * @param strNum
     * @param def
     * @return
     */
    public static int parseInt(String strNum, int def){
        if(strNum == null) return def;
        try{
            return Integer.parseInt(strNum);
        }catch(Exception e){
            return def;
        }
    }

    /**
     * String의 값이 null일 경우 ""로 변환하여 리턴한다.
     * @param temp
     * @return
     */
    public static String checkNull(String temp) {
        if (temp != null) {
            temp = temp.trim();
        } else {
            temp = "";
        }
        return temp;
    }

    /**
     * 1차원 배열의 값중 null인 값을 ""로 변환하여 리턴한다.
     * @param temp
     * @return
     */
    public static String[] checkNull(String[] temp){
        for(int i=0; i<temp.length; i++) {
            temp[i] = checkNull(temp[i]);
        }
        return temp;
    }

    /**
     * 2차원 배열의 값중 null인 값을 ""로 변환하여 리턴한다.
     * @param temp
     * @return
     */
    public static String[][] checkNull(String[][] temp) {
        for(int i=0; i<temp.length; i++) {
            temp[i][0] = checkNull(temp[i][0]);
            temp[i][1] = checkNull(temp[i][1]);
        }
        return temp;
    }

    /**
     * 스트링을 format 에 맞게 변환을 한다.
     * convertFormat("1", "00") return "01" 로 입력 값을 리턴한다.
     * @param inputStr
     * @param format
     * @return String
     */
    public static String convertFormat(String inputStr, String format){
        int _input = Integer.parseInt(inputStr);
        StringBuffer result = new StringBuffer();
        DecimalFormat df = new DecimalFormat(format);
        df.format( _input, result, new FieldPosition(1) );
        return result.toString();
    }

    /**
     *
     * @param str
     * @param outFormat
     * @return
     */
    public static String numberFormat(String str, String outFormat) {
        return new DecimalFormat(outFormat).format(str);
    }

    /**
     *
     * @param str
     * @return
     */
    public static String numberFormat(int num) {
        return NumberFormat.getNumberInstance().format(num);

    }

    /**
     *
     * @param str
     * @param oriEncode
     * @param newEncode
     * @return
     */
    public static String encoding(String str, String oriEncode, String newEncode) {
        str = checkNull(str);
        if(str.length() > 0) {
            try {
                str = new String(str.getBytes(oriEncode), newEncode);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return str;
    }


    /**
     * 구분자를 가지고 있는 문자열을 구분자를 기준으로 나누어주는 메소드
     * @param splittee 구분자를 가진 문자열
     * @param splitChar 구분자
     * @return
     */
    public static String[] split(String splittee, String splitChar){
        String taRetVal[] = null;
        StringTokenizer toTokenizer = null;
        int tnTokenCnt = 0;

        try {
            toTokenizer = new StringTokenizer(splittee, splitChar);
            tnTokenCnt = toTokenizer.countTokens();
            taRetVal = new String[tnTokenCnt];

            for(int i=0; i<tnTokenCnt; i++) {
                if(toTokenizer.hasMoreTokens())	taRetVal[i] = toTokenizer.nextToken();
            }
        } catch (Exception e) {
            taRetVal = new String[0];
        }
        return taRetVal ;
    }

    /**
     * String 을 받아 UTF-8 범위내 문자가 이닌경우 공백(0x0020) 으로 치환
     * @param str
     * @return String
     */
    public static String validate(String str) {
        StringBuffer buf = new StringBuffer();

        char ch;
        for(int i=0; i < str.length(); i++) {
            ch = str.charAt(i);
            if(Character.isLetterOrDigit(ch)) {
            } else {
                if(Character.isWhitespace(ch)) {
                } else {
                    if(Character.isISOControl(ch)) {
                        // UTF-8 에서 지원하지 않는 문자 제거
                        ch = (char)0x0020;
                    }
                }
            }

            buf.append(ch);
        }

        return buf.toString();
    }

    /**
     * request null체크
     **/
    public String getCheckReq(javax.servlet.http.HttpServletRequest req, String parameter, String default_value) {
        String req_value = (req.getParameter(parameter) == null ||  req.getParameter(parameter).equals("")) ? default_value : req.getParameter(parameter);
        return req_value;
    }

    /**
     * request Array null체크
     **/
    public String[] getCheckReqs(javax.servlet.http.HttpServletRequest req, String parameter, String[] default_value) {
        String[] req_value = req.getParameterValues(parameter);
        String[] tmp = null;
        int c = 0;
        if(req_value!=null) {
            tmp = new String[req_value.length];
            for(int i=0; i<req_value.length; i++) {
                tmp[c] = req_value[i];
                c++;
            }
        }
        req_value = req.getParameterValues(parameter)!=null ? tmp : default_value;
        return req_value;
    }

    public String replaceURL(String base, String url, String param, String value) {

        String sURL = "";
        if ( url != null && !url.equals("")) {
            if ( url.indexOf(param) < 0 )
                url = url + "&" + param + "=" + value;

            String [] params = url.split("&");
            for ( int idx=0; idx < params.length; idx++ ) {
                if ( params[idx].indexOf(param) >= 0 ) {
                    params[idx] = param + "=" + value;
                }

                sURL = sURL + params[idx] ;

                if ( idx + 1 < params.length)
                    sURL = sURL + "&" ;

            }

        } else {
            sURL = param + "=" + value;
        }

        sURL = base + "?" + sURL;


        return sURL;

    }

    /**
     * null체크
     **/
    public String nvl(String parameter, String default_value) {
        String req_value = parameter !=null ? parameter:default_value;
        return req_value;
    }

    /**
     * request null체크, uncoding
     **/
    public String getCheckReqUnocode(javax.servlet.http.HttpServletRequest req, String parameter, String default_value) {
        String req_value = req.getParameter(parameter)!=null ? encoding(req.getParameter(parameter), ENCODE_ORI, ENCODE_NEW):default_value;
        return req_value;
    }

    /**
     * request Array null체크, uncoding
     **/
    public String[] getCheckReqsUnocode(javax.servlet.http.HttpServletRequest req, String parameter, String[] default_value) {
        String[] req_value = req.getParameterValues(parameter);
        String[] tmp = null;
        int c = 0;
        if(req_value!=null) {
            tmp = new String[req_value.length];
            for(int i=0; i<req_value.length; i++) {
                tmp[c] = encoding(req_value[i], ENCODE_ORI, ENCODE_NEW);
                c++;
            }
        }
        req_value = req.getParameterValues(parameter)!=null ? tmp : default_value;
        return req_value;
    }

    /**
     * 인기검색어
     **/
    public String getPopKeyword() {
        String url ="http://127.0.0.1:7800/sample/normal/popword/popword.jsp?target=popword&range=D&collection=_ALL_";
        NodeList list = null;
        int count=0;
        StringBuffer sb = new StringBuffer();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //DOM parser factory 생성
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder(); //DOM parser 생성
            Document document = builder.parse(url); //xml 문서 파싱
            Element eRoot = document.getDocumentElement();
            NodeList data = eRoot.getElementsByTagName("Data");
            Element element = (Element) data.item(0);
            list = eRoot.getElementsByTagName("Query");
            count = list.getLength();

            if(list != null){
                for(int i=0; i	< count; i++){
                    Element eQuery = (Element)list.item(i);
                    String popQuery = eQuery.getTextContent();

                    sb.append("<li class=\"ranking\">");
                    sb.append("	<ul>");
                    sb.append("		<li class=\"ranktxt\"><img src=\"images/"+ eQuery.getAttribute("id")+".gif\" alt=\"\" /> <a href=\"#none\" onclick=\"javascript:doKeyword('" + eQuery.getTextContent() + "');\">" + eQuery.getTextContent() + "</a></li>");
                    sb.append("		<li class=\"rankico\">");

                    if (eQuery.getAttribute("updown") == "U") {
                        sb.append("<img src=\"images/ico_up.gif\" alt=\"상승\" />");
                    } else if (eQuery.getAttribute("updown") == "D") {
                        sb.append("<img src=\"images/ico_down.gif\" alt=\"하락\" />");
                    } else if (eQuery.getAttribute("updown") == "N") {
                        sb.append("<img src=\"images/ico_new.gif\" alt=\"신규\" />");
                    } else if (eQuery.getAttribute("updown") == "C") {
                        sb.append("-");
                    }

                    sb.append("		</li>");
                    sb.append("		<li class=\"rankstep\">" + eQuery.getAttribute("count") + "</li>");
                    sb.append("		");
                    sb.append("	</ul>");
                    sb.append("</li>");
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    /**
     * 문자의 길이를 지정된 길이만큼 잘라서 반환하는 함수<br>
     * 한글과 영문,숫자에 따라서 길이를 다르게 계산한다.
     * @param str 변환할 문자열<br>
     * byteLength 문자열의 길이
     * @return rtStr 지정된 길이로 수정된 문자열을 반환한다.
     */
    public static String getSubString(String str, int byteLength) {
        if (str==null) {
            return "";
        }

        StringBuffer rtStr = new StringBuffer();

        rtStr.append(str.substring(0,getLengthInString(str,byteLength)));

        if(rtStr.length() != str.length()) {
            rtStr.append("...");
        }

        return rtStr.toString();
    }


    /**
     * 문자의 길이를 한글 2자 영문,숫자를 1자로 계산하여<br>
     * 문자열의 길이를 반환하는 함수
     * @param str 변환할 문자열<br>
     * byteLength 문자열의 길이
     * @return int 문자열의 길이
     */
    public static int getLengthInString(String str, int byteLength)
    {
        int length = str.length();
        int retLength = 0;
        int tempSize = 0;
        int asc;

        for(int i = 1; i<=length; i++) {
            asc = (int)str.charAt(i-1);

            if(asc > 127) {
                if ( byteLength > tempSize ) {
                    tempSize += 2;
                    retLength++;
                }
            } else {
                if ( byteLength > tempSize ) {
                    tempSize++;
                    retLength++;
                }
            }
        }

        return retLength;
    }

    /**
     * request XSS 처리
     **/
    public String getCheckReqXSS(javax.servlet.http.HttpServletRequest req, String parameter, String default_value) {
        String req_value = (req.getParameter(parameter) == null ||  req.getParameter(parameter).equals("")) ? default_value : req.getParameter(parameter);
        req_value = req_value.replaceAll("</?[a-zA-Z][0-9a-zA-Z가-\uD7A3ㄱ-ㅎ=/\"\'%;:,._()\\-# ]+>","");
        req_value = req_value.replaceAll(">","");
        req_value = req_value.replaceAll(">","");
        return req_value;
    }

    /**
     * exquery 여부 확인
     *
     * @param search
     * @return
     */
    public String setExquery(Search search) {
        String exquery = "";

        String serviceCode = search.getServiceCode();
        if (!serviceCode.equals("")) exquery += mkExqueryString(serviceCode, "serviceCode");

        String tag = search.getTag();
        if (!tag.equals("")) exquery += mkExqueryString(tag, "tag");

        String discountTag = search.getDiscountTag();
        if (!discountTag.equals("")) exquery += mkExqueryString(discountTag, "discountTag");

        String serviceTag = search.getServiceTag();
        if (!serviceTag.equals("")) exquery += mkExqueryString(serviceTag, "serviceTag");

        String wine25ItemKindName = search.getWine25ItemKindName();
        if (!wine25ItemKindName.equals("")) exquery += mkExqueryString(wine25ItemKindName, "wine25ItemKindName");

        String wine25RegionSpName = search.getWine25RegionSpName();
        if (!wine25RegionSpName.equals("")) exquery += mkExqueryString(wine25RegionSpName, "wine25RegionSpName");

        String cardDiscountName = search.getCardDiscountName();
        if (!cardDiscountName.equals("")) exquery += mkExqueryString(cardDiscountName, "cardDiscountName");

        String cardDiscountYn = search.getCardDiscountYn();
        if (!cardDiscountYn.equals("")) exquery += mkExqueryString(cardDiscountYn, "cardDiscountYn");

        String adultYn = search.getAdultYn();
        if (!adultYn.equals("")) exquery += mkExqueryString(adultYn, "adultYn");

        String soldOutSp = search.getSoldOutSp();
        if (!soldOutSp.equals("")) exquery += mkExqueryString(soldOutSp, "soldOutSp");

        String deliverySp = search.getDeliverySp();
        if (!deliverySp.equals("")) exquery += mkExqueryString(deliverySp, "deliverySp");

        String stockCheckYn = search.getStockCheckYn();
        if (!stockCheckYn.equals("")) exquery += mkExqueryString(stockCheckYn, "stockCheckYn");

        String recommendItemYn = search.getRecommendItemYn();
        if (!recommendItemYn.equals("")) exquery += mkExqueryString(recommendItemYn, "recommendItemYn");

        String storeCode = search.getStoreCode();
        if (!"".equals(storeCode)) exquery += mkExqueryString(storeCode, "storeCode");

        String prmByQty = search.getPrmByQty();
        if (!"".equals(prmByQty)) exquery += mkExqueryString(prmByQty, "prmByQty");

        String prmGtQty = search.getPrmGtQty();
        if (!"".equals(prmGtQty)) exquery += mkExqueryString(prmGtQty, "prmGtQty");

        String stockSupermarketItemCode = search.getStockSupermarketItemCode();
        if (!"".equals(stockSupermarketItemCode)) exquery += mkExqueryString(stockSupermarketItemCode, "supermarketItemCode");

        String wdlvyItemSpCd = search.getWdlvyItemSpCd();
        if(!"".equals(wdlvyItemSpCd)) exquery += mkExqueryString(wdlvyItemSpCd, "wdlvyItemSpCd");

        String optItemIncldYn = search.getOptItemIncldYn();
        if(!"".equals(optItemIncldYn)) exquery += mkExqueryString(optItemIncldYn, "optItemIncldYn");

        /* 와인 */
        String wine25GradeCode = search.getWine25GradeCode();
        if(!"".equals(wine25GradeCode)) exquery += mkExqueryString(wine25GradeCode, "wine25GradeCode");

        String wine25GradeSpCode = search.getWine25GradeSpCode();
        if(!"".equals(wine25GradeSpCode)) exquery += mkExqueryString(wine25GradeSpCode, "wine25GradeSpCode");

        String wine25groupPurchaseTypeCode = search.getWine25groupPurchaseTypeCode();
        if(!"".equals(wine25groupPurchaseTypeCode)) exquery += mkExqueryString(wine25groupPurchaseTypeCode, "wine25groupPurchaseTypeCode");

        String wine25BodyFillingSpCode = search.getWine25BodyFillingSpCode();
        if(!"".equals(wine25BodyFillingSpCode)) exquery += mkExqueryString(wine25BodyFillingSpCode, "wine25BodyFillingSpCode");

        String wine25SugarContentSpCode = search.getWine25SugarContentSpCode();
        if(!"".equals(wine25SugarContentSpCode)) exquery += mkExqueryString(wine25SugarContentSpCode, "wine25SugarContentSpCode");

        String wine25BreedSpCode = search.getWine25BreedSpCode();
        if(!"".equals(wine25BreedSpCode)) exquery += mkExqueryString(wine25BreedSpCode, "wine25BreedSpCode");

        String wine25RegionSpCode = search.getWine25RegionSpCode();
        if(!"".equals(wine25RegionSpCode)) exquery += mkExqueryString(wine25RegionSpCode, "wine25RegionSpCode");

        String wine25ItemKindCode = search.getWine25ItemKindCode();
        if(!"".equals(wine25ItemKindCode)) exquery += mkExqueryString(wine25ItemKindCode, "wine25ItemKindCode");

        String groupPurchaseYn = search.getGroupPurchaseYn();
        if(!"".equals(groupPurchaseYn)) exquery += mkExqueryString(groupPurchaseYn, "groupPurchaseYn");

        String svcKindCd = search.getSvcKindCd();
        if(!"".equals(svcKindCd)) exquery += mkExqueryString(svcKindCd, "svcKindCd");

        String townCode = search.getTownCode();
        if(!"".equals(townCode)) exquery += mkExqueryString(townCode, "townCode");

        String storePropertyCode = search.getStorePropertyCode();
        if(!"".equals(storePropertyCode)) exquery += mkExqueryString(storePropertyCode, "storePropertyCode");

        return exquery;
    }

    /**
     * exquery 생성
     *
     * @param parameter
     * @param prefixFieldName
     * @return String exquery
     */
    public String mkExqueryString(String parameter, String prefixFieldName) {
        String exquery = "";

        String[] parameterArrays = parameter.split(",");
        exquery += "(";
        for (int i = 0; i < parameterArrays.length; i++) {
            if (i == parameterArrays.length - 1)
                exquery += "<" + prefixFieldName + ":contains:" + parameterArrays[i] + ">";
            else exquery += "<" + prefixFieldName + ":contains:" + parameterArrays[i] + "> | ";
        }
        exquery += ") ";

        return exquery;
    }

    public String setFilterQuery(Search search) {
        String filterQuery = "";
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        /* 가격 정보 */
        filterQuery += "(<sellPrice:gt:"+search.getMinSellPrice() + "> ";
        filterQuery += "<sellPrice:lt:"+search.getMaxSellPrice() + "> )";
        /* 픽업 날짜 */
        //filterQuery += "(<pickupStartDate:lt:"+now+"> ";
        //filterQuery += "<pickupEndDate:gt:"+now+">)";

        return filterQuery;
    }
}
