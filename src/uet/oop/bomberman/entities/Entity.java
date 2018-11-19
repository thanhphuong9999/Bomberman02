package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.IRender;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Coordinates;

/**
 * L?p ??i di?n cho t?t c? th?c th? trong game (Bomber, Enemy, Wall, Brick,...)
 */
public abstract class Entity implements IRender {

	protected double _x, _y;
	protected boolean _removed = false;
	protected Sprite _sprite;

	/**
	 * Ph??ng th?c này ???c g?i liên t?c trong vòng l?p game,
	 * m?c ?ích ?? x? lý s? ki?n và c?p nh?t tr?ng thái Entity
	 */
	@Override
	public abstract void update();

	/**
	 * Ph??ng th?c này ???c g?i liên t?c trong vòng l?p game,
	 * m?c ?ích ?? c?p nh?t hình ?nh c?a entity theo tr?ng thái
	 */
	@Override
	public abstract void render(Screen screen);
	
	public void remove() {
		_removed = true;
	}
	
	public boolean isRemoved() {
		return _removed;
	}
	
	public Sprite getSprite() {
		return _sprite;
	}

	/**
	 * Ph??ng th?c này ???c g?i ?? x? lý khi hai entity va ch?m vào nhau
	 * @param e
	 * @return
	 */
	public abstract boolean collide(Entity e);
	
	public double getX() {
		return _x;
	}
	
	public double getY() {
		return _y;
	}
	
	public int getXTile() {
		return Coordinates.pixelToTile(_x + _sprite.SIZE / 2);
	}
	
	public int getYTile() {
		return Coordinates.pixelToTile(_y - _sprite.SIZE / 2);
	}
}