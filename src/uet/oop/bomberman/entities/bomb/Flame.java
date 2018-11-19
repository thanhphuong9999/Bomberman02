package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.enemy.Enemy;
public class Flame extends Entity {

	protected Board _board;
	protected int _direction;
	private int _radius;
	protected int xOrigin, yOrigin;
	protected FlameSegment[] _flameSegments = new FlameSegment[0];

	/**
	 *
	 * @param x hoanh do cua Flame
	 * @param y tung do cua Flame
	 * @param direction là huong cua Flame
	 * @param radius do dai cua Flame
	 */
	public Flame(int x, int y, int direction, int radius, Board board) {
		xOrigin = x;
		yOrigin = y;
		_x = x;
		_y = y;
		_direction = direction;
		_radius = radius;
		_board = board;
		createFlameSegments();
	}

	/**
	 * Tao cac FlameSegment, moi segment ung voi mot don vi do dai
	 */
	private void createFlameSegments() {
		 // tinh toan do dai Flame, tuon ung voi so luong segment
                 // bien last dùng de dánh dau cho segment cuoi cùng
                 boolean last;
		//_flameSegments = new FlameSegment[calculatePermitedDistance()];
                // TODO: tao các segment duoi dây
                int x = (int)_x;
                int y = (int)_y;
                for(int i = 0; i < _flameSegments.length; i++){
                    last =( i == _flameSegments.length - 1);
                    switch(_direction){
                        case 0: y--; break;
                        case 1: x++; break;
                        case 2: y++; break;
                        case 3: x--; break;
                    }
                    _flameSegments[i] = new FlameSegment(x, y, _direction, last);
                }
	}

	/**
	 * tinh toan do dai cua Flame, neu gap vat can là Brick/Wall, do dài se bi cat ngan
	 * @return
	 */
	private int calculatePermitedDistance() {
		// TODO: thuc hien tính toán do dài cua Flame
                int radius = 0;
                int xa = (int)_x;
                int ya = (int)_y;
                while(radius < _radius){
                    if(_direction == 0) ya--;
                    if(_direction == 1) xa++;
                    if(_direction == 2) ya++;
                    if(_direction == 3) xa--;
                    
                    Entity e = _board.getEntity(xa, ya, null);
                    if(e instanceof  Character){
                        radius++;
                    }
                    if(e.collide(this) == false){
                        break;
                    }
                    radius++;
                }
		return radius;
	}
	
	public FlameSegment flameSegmentAt(int x, int y) {
		for (int i = 0; i < _flameSegments.length; i++) {
			if(_flameSegments[i].getX() == x && _flameSegments[i].getY() == y)
				return _flameSegments[i];
		}
		return null;
	}

	@Override
	public void update() {}
	
	@Override
	public void render(Screen screen) {
		for(int i = 0; i < _flameSegments.length; i++) {
			_flameSegments[i].render(screen);
		}
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: xu ly va cham voi Bomber, Enemy. Chú ý doi tuong này có vi trí chính là vi trí cua Bomb da no
		if(e instanceof Bomber){
                    ((Bomber) e).kill();
                    return false;
                }
                if(e instanceof Enemy){
                    ((Enemy) e).kill();
                    return false;
                }
                return true;
	}
}