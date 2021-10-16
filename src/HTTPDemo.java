import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


public class HTTPDemo {
    static HttpURLConnection conn;
    static BufferedReader br;
    static String line;
    static StringBuilder res;
    public static void main(String args[])
    {
        try {
            URL url=new URL("https://seller.flipkart.com/api-docs/order-api-docs/v3/PostShipmentSearch.html#getshipment-label");
            conn= (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

            int status=conn.getResponseCode();
            System.out.println(status);

            if(status>299)
            {
                br=new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while((line= br.readLine())!=null)
                {
                    res.append(line);
                }
            }
            else
            {
                br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while((line= br.readLine())!=null)
                {
                    res.append(line);
                }
            }
            System.out.println(res);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            conn.disconnect();
        }

    }
}
