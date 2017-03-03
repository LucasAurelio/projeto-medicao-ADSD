package primeiraAplicacao;

public class ListaDeCompras {
	private long begin, end;
	 
    public synchronized void start(){
        begin = System.currentTimeMillis();
    }
 
    public synchronized void stop(){
        end = System.currentTimeMillis();
    }
 
    public long getTime() {
        return end-begin;
    }
 
    public long getMilliseconds() {
        return end-begin;
    }
 
    public double getSeconds() {
        return (end - begin) / 1000.0;
    }
 
    public double getMinutes() {
        return (end - begin) / 60000.0;
    }
 
    public double getHours() {
        return (end - begin) / 3600000.0;
    }
	    
	public static void main(String[] args){
		
		final ListaDeCompras chronometer = new ListaDeCompras();
		
		//MYSQL
		final BancoDeDados ourDB = new BancoDeDados();
		ourDB.connect();
		if(ourDB.isConnected()){
			//1
			chronometer.start();
			ourDB.insertItem("Beterraba",1);
			chronometer.stop();
			System.out.println("            Tempo SQL-inserção-1pc: "+chronometer.getMilliseconds()+"ms");
			ourDB.listItems();
			//2
			chronometer.start();
			ourDB.removeItem("Beterraba");
			chronometer.stop();
			System.out.println("           Tempo SQL-remoção-1pc: "+chronometer.getMilliseconds()+"ms");
			ourDB.listItems();
			//3
			chronometer.start();
			ourDB.insertItemsimult("Macaxeira", 1, "Batata", 6);
	    	chronometer.stop();
	    	System.out.println("           Tempo SQL-inserção-2pcs: "+chronometer.getMilliseconds()+"ms");
	    	ourDB.listItems();
	    	//4
	    	chronometer.start();
			ourDB.removeItemsimult("Macaxeira","Batata");
	    	chronometer.stop();
	    	System.out.println("           Tempo SQL-remoção-2pcs: "+chronometer.getMilliseconds()+"ms");
	    	ourDB.listItems();
	    	
			//end of SQL part
			ourDB.disconnect();
		}else{
			System.out.println("Não foi possível conectar ao banco de dados");
		}
		
		
		BDMongo ourMongoDB = new BDMongo();
		ourMongoDB.connectMongo();
		if(ourMongoDB.isMongoConnected()){
			//1
	        chronometer.start();
	        ourMongoDB.insterItemMongo("arroz", 3);
	        chronometer.stop();
	        System.out.println("           Tempo MONGO-inserção-1pc: "+chronometer.getMilliseconds()+"ms");
	        ourMongoDB.listCollectionItems();
	        //2
	        chronometer.start();
			ourMongoDB.removeItemMongo("arroz");
			chronometer.stop();
			System.out.println("           Tempo MONGO-remoção-1pc: "+chronometer.getMilliseconds()+"ms");
	        ourMongoDB.listCollectionItems();
	        //3
	        chronometer.start();
			ourMongoDB.insertItemSimultMongo("feijão", 1, "farinha", 1);
			chronometer.stop();
			System.out.println("           Tempo MONGO-inserção-2pcs: "+chronometer.getMilliseconds()+"ms");
	        ourMongoDB.listCollectionItems();
	        //4
	        chronometer.start();
			ourMongoDB.removeItemSimultMongo("feijão","farinha");
			chronometer.stop();
			System.out.println("           Tempo MONGO-remoção-2pcs: "+chronometer.getMilliseconds()+"ms");
	        ourMongoDB.listCollectionItems();
		}else{
			System.out.println("Não foi possível conectar ao banco de dados do Mongo");
		}
	}
}
