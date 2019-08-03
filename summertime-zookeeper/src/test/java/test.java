import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author L99
 * @createDate 2019/8/3
 *
 */
public class test {

    @Test
    public void portTest(){
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            System.out.println(hostAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
