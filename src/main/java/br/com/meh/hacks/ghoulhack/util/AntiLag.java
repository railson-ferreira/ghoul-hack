package br.com.meh.hacks.ghoulhack.util;
///Inativo////
public class AntiLag {
	static long delay = 2000; //em milesimos de segundo [1000 = 1segundo]
	static long tempo = 0;
	public static boolean usar(){
		if(tempo == 0){
			tempo = new java.util.Date().getTime();
			return true;
		} else {
			long agora = new java.util.Date().getTime();
			if(agora - tempo > delay){
				return true;
			} else {
				return false;
			}
		}
	}
}
