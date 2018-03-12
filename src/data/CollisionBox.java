package data;

public class CollisionBox {
	private double posX, posY;
	private double width, height;

	public CollisionBox(double posX, double posY, double width, double height) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
	}
	
	public CollisionBox(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public void setPosXY(double posX, double posY){
		this.posX = posX;
		this.posY = posY;
	}
	
	public boolean isCollided(CollisionBox other) {
		boolean result = true;
		if (this.posX > other.posX + other.width || this.posX + this.width < other.posX
				|| this.posY > other.posY + other.height || this.posY + this.height < other.posY) {
			result = false;
		}
		return result;
	}
}
