package api;

// 네이버 검색 API 예제 - 블로그 검색
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//네이버 검색 API예제는 블로그를 비롯 전문자료까지 호출방법이 동일하므로
//blog검색만 대표로 예제를 올렸습니다.
//네이버 검색 API 예제 - blog 검색

// 애너테이션으로 요청명과 이 서블릿을 매
@WebServlet("/NaverSearchAPI.do")
public class SearchAPI extends HttpServlet{
	private static final long serialVersionUID = 1L;


	//네이버 검색 API예제는 블로그를 비롯 전문자료까지 호출방법이 동일하므로
	//blog검색만 대표로 예제를 올렸습니다.
	//네이버 검색 API 예제 - blog 검색

	// 애너테이션으로 요청명과 이 서블릿을 매핑 처리 합니다.

    @Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 인증 정보 설정
    	String clientId = "여기에 네이버API 아이디 기재함"; //애플리케이션 클라이언트 아이디
    	String clientSecret = "여기에 네이버 검색 API 시크릿값 기재함"; //애플리케이션 클라이언트 시크릿
    	
    	//2. 검색 조건 설정
    	int startNum = 0; // 검색 시작 위치
    	String text = null; //검색어 처리 변수 text 선언 및 초기화
    	
    	try {
        	// 검색 시작 위치(startNum), 검색어(keyword)를 매개변수(Parameter)로 받습니다.
        	startNum = Integer.parseInt(req.getParameter("startNum"));
        	String searchText = req.getParameter("keyword");
        	
        	// 검색어는 한글 깨짐을 방지하기 위해서 UTF-8로 인코딩 처리 합니다.
        	text = URLEncoder.encode(searchText,"utf-8");
        				
		} catch (Exception e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}
        // 3. API URL 조합 : 검색 결과 데이터를 JSON으로 받기 위한 API 입니다.
        //                 검색어(text)를 쿼리스트링으로 보내는데,
        //                 여기에 display와 start 매개변수도 추가합니다.
        //                 display는 한 번에 가져올 검색 결과의 개수이며,
        //                 start는 검색 시작 위치입니다.
        String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text + "&display=10&start=" + startNum;    // JSON 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // XML 결과
        
        // 4. API 호출 : 클라이언트 아이디와 시크릿키를 요청헤더로 전달해 API를 호출합니다.

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);
    	
        // 5. 결과 출력 : 검색 결곽를 콘솔에 출력하고 
        System.out.println(responseBody);
        // 서블릿에서도 즉시 출력하기 위해 콘텐츠 타입을 설정한 후
        resp.setContentType("text/html; charset=utf-8");
        // write() 메서드를 호출합니다.
        resp.getWriter().write(responseBody); // 서블릿에서 즉시 출력 처리합니다.
	}


    

// 네이버 개발자 센터에서 가져온 get (), connect(), readBody() 메서드는
// 수정할 내용이 없으므로 그대로 사용하시면 됩니다.
    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
}