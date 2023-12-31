import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class CheckReservation {
    private WebDriver driver;
    int count = 0;
    private String url;
    
    private Set<String> mailedTime = new HashSet<>();
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String WEB_DRIVER_PATH = "C:/Park/JAVA/Selenium_EX/CheckReservateion/chromedriver-win64/chromedriver.exe";
        
    public CheckReservation(){
        // WebDriver 경로 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        // WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blokcing");
        
        driver = new ChromeDriver(options);

        url = "https://www.keyescape.co.kr/web/home.php?go=rev.make";
    }
    public void activateBot(){
        count++;
        WebElement element_jijum = null;
        WebElement element_day = null;
        WebElement element_theme = null;
        WebElement element_time_info= null;
        try{
            driver.get(url);
            Thread.sleep(2000); // 페이지 로딩 대기시간

            // 메모리 컴퍼니 클릭
            element_jijum = driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/div[4]/dl[1]/dd/div/ul/a[3]"));
            element_jijum.click();
            Thread.sleep(1000);
            // 달력 날짜 선택
            for(int week = 2; week<=7; week++){
                for(int day = 1; day<=7; day++){
                    // 날짜 특정
                    if(week!=6 || day!=2) continue;
                    try{
                        element_day = driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/div[4]/dl[2]/dd/div/div/div/table/tbody/tr["+week+"]/td["+day+"]/a"));
                        element_day.click();
                        Thread.sleep(1000);
                        System.out.println(element_day.getText());
                    }catch(Exception e){
                        continue;
                    }                    

                    int idx_theme = 3;// default : 1 
                    while(true){
                        try{
                            element_theme = null;
                            element_theme = driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/div[4]/dl[3]/dd/div/ul/a["+idx_theme+"]"));
                            element_theme.click();
                            Thread.sleep(3000);
                            System.out.println(element_theme.getText());
                            
                            int idx_time = 1;
                            while(true){
                                try{
                                    element_time_info=null;
                                    element_time_info =                      
                                        driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/div[4]/dl[4]/dd/div/ul/a["+idx_time+"]/li"));
                                    //System.out.println(element_time_info.getText());
                                    String time = element_time_info.getText();
                                    if(mailedTime.contains(time)) continue;
                                    System.out.println(element_time_info.getText());
                                    // new SMTP().sendMail("toEmail", element_theme.getText(), element_theme.getText(), time);
                                    System.out.println("메일 전송");
                                    mailedTime.add(time);
                                }catch(Exception e2){
                                    System.out.println("    --- 남는 시간 없음");
                                    break;
                                }
                                idx_time++;
                            }
                            
                        }catch(Exception e){
                            System.out.println("                --- 다음 테마 없음");
                            break;
                        }
                        idx_theme++;
                    }
                              
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //driver.close();
            // 브라우저 종료
            System.out.println("count : "+count);
        }
    }
    public static void main(String[] args) throws Exception {
        CheckReservation app = new CheckReservation();
        Timer scheduler = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                app.activateBot();
            }
        };
        scheduler.scheduleAtFixedRate(task, 1000, 30000);
    }
}
