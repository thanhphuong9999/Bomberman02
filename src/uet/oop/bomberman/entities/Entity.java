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
	 * Ph??ng th?c n�y ???c g?i li�n t?c trong v�ng l?p game,
	 * m?c ?�ch ?? x? l� s? ki?n v� c?p nh?t tr?ng th�i Entity
	 */
	@Override
	public abstract void update();

	/**
	 * Ph??ng th?c n�y ???c g?i li�n t?c trong v�ng l?p game,
	 * m?c ?�ch ?? c?p nh?t h�nh ?nh c?a entity theo tr?ng th�i
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
	 * Ph??ng th?c n�y ???c g?i ?? x? l� khi hai entity va ch?m v�o nhau
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