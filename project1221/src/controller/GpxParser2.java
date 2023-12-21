package controller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class GpxParser2 {

    // GPX 파일 목록을 받아와서 좌표 정보를 추출한 후 파일별로 맵에 저장하는 메서드
    public static Map<String, ArrayList<String>> parseGpx(ArrayList<File> files) {
        // 결과를 저장할 Map: 파일 이름을 key로, 좌표 정보 리스트를 value로 함
        Map<String, ArrayList<String>> coordinatesMap = new HashMap<>();

        try {
            // 입력으로 받은 각 파일에 대해 반복
            for (File file : files) {
                // 파일에서 XML을 읽어오기 위한 준비
                File gpxFile = new File(file.getPath());
                //XML로 파싱하기 위해 DocumentBuilderFactory 객체 생성 .newInstance를 이용해서 기본적인 형태 생성
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                //파싱을 위한 객체 구현
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(gpxFile);

                // XML 문서의 구조를 표준화
                doc.getDocumentElement().normalize();

                // GPX 파일에서 <trkpt> 요소를 찾아서 경도와 위도 정보 추출
                NodeList trkptList = doc.getElementsByTagName("trkpt");

                // 각 파일별로 좌표 정보를 저장할 리스트
                ArrayList<String> coordinates = new ArrayList<>();

                // NodeList에서 각 "trkpt" 요소를 순회하며 좌표 정보를 추출
                for (int temp = 0; temp < trkptList.getLength(); temp++) {
                    Element trkptElement = (Element) trkptList.item(temp);
                    // "trkpt" 요소에서 "lat"와 "lon" 속성을 추출하여 좌표 정보로 조합 후 리스트에 추가
                    String lat = trkptElement.getAttribute("lat");
                    String lon = trkptElement.getAttribute("lon");
                    coordinates.add(lat + ',' + lon);
                }

                // 맵에 파일 이름과 좌표 정보 리스트를 저장
                coordinatesMap.put(file.getName(), coordinates);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return coordinatesMap;
    }
}