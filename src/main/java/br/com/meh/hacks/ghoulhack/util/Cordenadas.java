package br.com.meh.hacks.ghoulhack.util;

public class Cordenadas {
	private double X;
	private double Y;
	private double Y_OLHOS;
	private double Z;
	private float PITCH;
	private float YAW;
	public Cordenadas(double x, double y, double z, float yaw, float pitch){
		X = x;
		Y = y;
		Y_OLHOS = Y+1.62;
		Z = z;
		PITCH = pitch;
		YAW = yaw;
	}
	public Cordenadas(double x, double y, double y_olhos, double z, float yaw, float pitch){
		X = x;
		Y = y;
		Y_OLHOS = y_olhos;
		Z = z;
		PITCH = pitch;
		YAW = yaw;
	}
	public double posX(){
		return X;
	}
	public double posY(){
		return Y;
	}
	public double posY_OLHOS(){
		return Y_OLHOS;
	}
	public double posZ(){
		return Z;
	}
	public float rotacaoPitch(){
		return PITCH;
	}
	public float rotacaoYaw(){
		return YAW;
	}
	public void definir(double x, double y, double z, float yaw, float pitch){
		X = x;
		Y = y;
		Z = z;
		PITCH = pitch;
		YAW = yaw;
	}
	public void definir(double x, double y, double y_olhos, double z, float yaw, float pitch){
		X = x;
		Y = y;
		Y_OLHOS = y_olhos;
		Z = z;
		PITCH = pitch;
		YAW = yaw;
	}
	public double CalcularDistancia(Cordenadas cords){
		//Em Teste
		double x1 = X;
		double y1 = Y;
		double z1 = Z;
		
		double x2 = cords.posX();
		double y2 = cords.posY();
		double z2 = cords.posZ();

		double minX; double minY; double minZ;
		double maxX; double maxY; double maxZ;	

		if(x1 > x2){maxX = x1; minX = x2;}else{minX = x1; maxX = x2;}
		if(y1 > y2){maxY = y1; minY = y2;}else{minY = y1; maxY = y2;}
		if(z1 > z2){maxZ = z1; minZ = z2;}else{minZ = z1; maxZ = z2;}
		
		double x = maxX - minX; double y = maxY - minY; double z = maxZ - minZ;
		
		//XZ² = X² + Z²    //    XZ = Math.sqrt(X² + Z²)
		double quadradoX = Math.pow(x, 2);
		double quadradoY = Math.pow(y, 2);
		double quadradoZ = Math.pow(z, 2);
		double distanciaXZ = Math.sqrt(quadradoX + quadradoZ);
		double quadradoXZ = Math.pow(distanciaXZ, 2);
		double distanciaXYZ = Math.sqrt(quadradoXZ + quadradoY); 
		
		return distanciaXYZ;
	}
	public double CalcularDistanciaXZ(Cordenadas cords){
		double x1 = X;
		double z1 = Z;
		
		double x2 = cords.posX();
		double z2 = cords.posZ();

		double minX; double minZ;
		double maxX; double maxZ;	

		if(x1 > x2){maxX = x1; minX = x2;}else{minX = x1; maxX = x2;}
		if(z1 > z2){maxZ = z1; minZ = z2;}else{minZ = z1; maxZ = z2;}
		
		double x = maxX - minX; double z = maxZ - minZ;
		
		//XZ² = X² + Z²    //    XZ = Math.sqrt(X² + Z²)
		double quadradoX = Math.pow(x, 2);
		double quadradoZ = Math.pow(z, 2);
		double distanciaXZ = Math.sqrt(quadradoX + quadradoZ);
		
		return distanciaXZ;
	}
	public Cordenadas CalcularDiferenca(Cordenadas cords){
		double x1 = X;
		double y1 = Y;
		double z1 = Z;
		float pitch1 = PITCH;
		float yaw1 = YAW;
		
		double x2 = cords.posX();
		double y2 = cords.posY();
		double z2 = cords.posZ();
		float pitch2 = cords.rotacaoPitch();
		float yaw2 = cords.rotacaoYaw();
		
		double x = x2 - x1; double y = y2 - y1; double z = z2 - z1;
		float pitch = pitch2 - pitch1; float yaw = yaw2 - yaw1;
		
		Cordenadas retorno = new Cordenadas(x, y, z, pitch, yaw);
		return retorno;
	}
	public Cordenadas Olhar(double x, double y, double z){
		//Mudar o Pitch//
		Cordenadas antiga_cords = this;
		double distancia = CalcularDistanciaXZ(new Cordenadas(x, y, z, 0, 0));
		double altura = Y_OLHOS - y;
		if(altura < 0){
			double tg_pitch = distancia / Math.abs(altura);
			double pitch = Math.toDegrees(Math.atan(tg_pitch));
			double reverso_pitch = 90 - pitch;
			PITCH = (float) -reverso_pitch;
		}
		else if(altura > 0){
			double tg_pitch = distancia / Math.abs(altura);
			double pitch = Math.toDegrees(Math.atan(tg_pitch));
			double reverso_pitch = 90 - pitch;
			PITCH = (float) reverso_pitch;
		} else {
			PITCH = 0;
		}
		//Mudar o Yaw//
		Cordenadas diferenca = CalcularDiferenca(new Cordenadas(x, y, z, 0, 0));
		double diferencaX = diferenca.posX();
		double diferencaZ = diferenca.posZ();
		double tg_yaw = Math.abs(diferencaX) / Math.abs(diferencaZ);
		float yaw = (float) Math.toDegrees(Math.atan(tg_yaw));
		if(diferencaX > 0){
			if(diferencaZ > 0){
				YAW = 0 - yaw;
			} else if(diferencaZ < 0){
				YAW = 0 - 180 + yaw;
			} else {
				YAW = 0 - 90;
			}
		} else if(diferencaX < 0){
			if(diferencaZ > 0){
				YAW = yaw;
			} else if(diferencaZ < 0){
				YAW = 180 - yaw;
			} else {
				YAW = 90;
			}
		} else {
			if(diferencaZ > 0){
				YAW = 0;
			} else if(diferencaZ < 0){
				YAW = 180;
			}
		}
		return antiga_cords;
	}
	public void PegarLocalOlhar(){
		
	}
}
