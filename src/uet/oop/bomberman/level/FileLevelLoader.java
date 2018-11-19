package uet.oop.bomberman.level;

import java.io.*;
import java.util.*;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class FileLevelLoader extends LevelLoader {

	// Ma tran chua th�ng tin ban do, moi phan tu luu gi� tri k� tu doc duoc
	// tu ma tran ban do trong tep cau h�nh
	
        private static char[][] _map;
	
	public FileLevelLoader(Board board, int level) throws LoadLevelException {
		super(board, level);
	}
	
	@Override
	public void loadLevel(int level) {
            // TODO: doc du lieu tu tep cau h�nh /levels/Level{level}.txt
            // TODO: cap nhat c�c gi� tri doc duoc v�o _width, _height, _level, _map
            
            ArrayList <String> s = new ArrayList<>();
            String path = "res/levels/Level" + level + ".txt";
            BufferedReader abs = null;
            try {
                abs = new BufferedReader(new FileReader(new File(path)));
                String line;
                while((line = abs.readLine()) != null){
                    s.add(line);
                }
                
            } 
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
            
            String []arr = s.get(0).trim().split(" ");
            _level = Integer.parseInt(arr[0]);
            _height = Integer.parseInt(arr[1]);
            _width = Integer.parseInt(arr[2]);
            _map = new char[_height][_width];
            
            for(int i = 0; i < _height ; i++){
                for(int j = 0; j < _width; j++){
                    _map[i][j] = s.get(i+1).charAt(j);
                }
            }
	}

	@Override
	public void createEntities() {
		// TODO: tao c�c Entity cua m�n choi
		// TODO: sau khi tao xong, goi _board.addEntity() ?? th�m Entity v�o game

		// TODO: phan code mau o duoi de huong dan c�ch th�m c�c loai Entity v�o game
		// TODO: h�y x�a n� khi ho�n th�nh ch?c n?ng load m�n choi tu tep cau h�nh
		// th�m Wall
                
            for (int y = 0; y < getHeight(); y++){
                for (int x = 0; x < getWidth(); x++){
                
                    int pos = x + y * getWidth();

                    switch (_map[y][x]){
                        case '#': 
                            _board.addEntity(pos, new Wall(x, y, Sprite.wall));
                            break;
                        
                        case 'p': 
                            _board.addCharacter(new Bomber(Coordinates.tileToPixel(x), 
                                                Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                            Screen.setOffset(0, 0);
                            _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                            break;
                        
                        case '*': 
                            _board.addEntity(pos,new LayeredEntity(x, y,
                                        new Grass(x, y, Sprite.grass),
                                        new Brick(x, y, Sprite.brick)));
                            break;
                        
                        case '1': 
                            _board.addCharacter(new Balloon(Coordinates.tileToPixel(x), 
                                            Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                            _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                            break;
                        
                        case '2': 
                            _board.addCharacter(new Oneal(Coordinates.tileToPixel(x), 
                                            Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                            _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                            break;
                        
                        case 'x': 
                            _board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass),
                                new Portal(x, y, Sprite.portal),
                                new Brick(x, y, Sprite.brick)));
                            break;
                   
                        case 'f': 
                            _board.addEntity(pos,
                                new LayeredEntity(x, y,new Grass(x, y, Sprite.grass),
                                                new SpeedItem(x, y, Sprite.powerup_flames),
                                                new Brick(x, y, Sprite.brick)));
                            break;
                    
                        default:
                            _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                            break;
                    }
                }
            }
                
                /*
		for (int x = 0; x < 20; x++) {
			for (int y = 0; y < 20; y++) {
				int pos = x + y * _width;
				Sprite sprite = y == 0 || x == 0 || x == 10 || y == 10 ? Sprite.wall : Sprite.grass;
				_board.addEntity(pos, new Grass(x, y, sprite));
			}
		}

		// th�m Bomber
		int xBomber = 1, yBomber = 1;
		_board.addCharacter( new Bomber(Coordinates.tileToPixel(xBomber), Coordinates.tileToPixel(yBomber) + Game.TILES_SIZE, _board) );
		Screen.setOffset(0, 0);
		_board.addEntity(xBomber + yBomber * _width, new Grass(xBomber, yBomber, Sprite.grass));

		// th�m Enemy
		int xE = 2, yE = 1;
		_board.addCharacter( new Balloon(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
		_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));

		// th�m Brick
		int xB = 3, yB = 1;
		_board.addEntity(xB + yB * _width,
				new LayeredEntity(xB, yB,
					new Grass(xB, yB, Sprite.grass),
					new Brick(xB, yB, Sprite.brick)
				)
		);

		// th�m Item k�m Brick che ph? ? tr�n
		int xI = 1, yI = 2;
		_board.addEntity(xI + yI * _width,
				new LayeredEntity(xI, yI,
					new Grass(xI ,yI, Sprite.grass),
					new SpeedItem(xI, yI, Sprite.powerup_flames),
					new Brick(xI, yI, Sprite.brick)
				)
		);
            */
	}

}