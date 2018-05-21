package ktmt.rssreader;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class AppConstant {
    private static Bus bus;
    
    public static Bus getBus(){
        if(bus == null){
            bus = new Bus(ThreadEnforcer.ANY);
        }
        return bus;
    }
}
