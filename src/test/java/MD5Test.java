import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;

public class MD5Test {
        @Test
        public void md5Test(){
            Md5Hash pwd = new Md5Hash("123456","salt-aaa");
            System.out.println(pwd);//0d465361964afe81fb40e57f6bdf8d19
        }
}
