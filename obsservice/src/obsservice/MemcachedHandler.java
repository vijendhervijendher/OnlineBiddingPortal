package obsservice;

import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class MemcachedHandler {
	
	MemcachedClient mcc;
    
	public MemcachedHandler() throws Exception{
		
		mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
	}
	public String tryGet(String Key){
		
		if(mcc.get(Key)== null){
			return "null";
		}
		return mcc.get(Key).toString();
	}
	public void Put(String Key,String Value){
		
		mcc.set(Key,30*60,Value);
	}	
	public void tryInvalidate(String Key){
		
		mcc.delete(Key);
	}
	
	public void Destroy(){
		mcc.shutdown();
	}
}
