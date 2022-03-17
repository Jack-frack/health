import com.aliyuncs.exceptions.ClientException;
import com.lizhao.utils.SMSUtils;
import org.junit.Test;

public class test {

   // @Test
    public static void main(String[] args) throws ClientException {
        SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,"13205299221","2022");
    }
}
