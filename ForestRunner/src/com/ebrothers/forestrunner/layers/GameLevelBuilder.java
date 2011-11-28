package com.ebrothers.forestrunner.layers;

import java.util.ArrayList;

import org.cocos2d.nodes.CCNode;

import com.ebrothers.forestrunner.common.Globals;
import com.ebrothers.forestrunner.common.Logger;
import com.ebrothers.forestrunner.data.LevelData;
import com.ebrothers.forestrunner.data.LevelData.SpriteData;
import com.ebrothers.forestrunner.sprites.Box;
import com.ebrothers.forestrunner.sprites.Bridge;
import com.ebrothers.forestrunner.sprites.Dinosaur1;
import com.ebrothers.forestrunner.sprites.Dinosaur2;
import com.ebrothers.forestrunner.sprites.Dinosaur3;
import com.ebrothers.forestrunner.sprites.Fire;
import com.ebrothers.forestrunner.sprites.Flower;
import com.ebrothers.forestrunner.sprites.GameSprite;
import com.ebrothers.forestrunner.sprites.GoSign;
import com.ebrothers.forestrunner.sprites.GroundL;
import com.ebrothers.forestrunner.sprites.GroundM1;
import com.ebrothers.forestrunner.sprites.GroundM2;
import com.ebrothers.forestrunner.sprites.GroundR;
import com.ebrothers.forestrunner.sprites.SpriteType;
import com.ebrothers.forestrunner.sprites.Stone;
import com.ebrothers.forestrunner.sprites.StopSign;
import com.ebrothers.forestrunner.sprites.Trap;

public class GameLevelBuilder {
	private static final String TAG = "GameLevelBuilder";
	private float levelWidth = 0;

	public static GameLevelBuilder create() {
		GameLevelBuilder map = new GameLevelBuilder();
		return map;
	}

	public void build(CCNode parent, LevelData levelData) {
		Logger.d(TAG, "build level map...");
		assert (levelData != null);
		// get sprite's data
		ArrayList<SpriteData> datas = levelData.getSpriteDatas();
		float nextX = 0;
		float lastGroundLeft = 0;
		float lastGroundTop = Globals.groundM_y;
		boolean paddingLeft = false;
		for (SpriteData spriteData : datas) {
			// create game sprite by type
			switch (spriteData.type) {
			case SpriteType.GROUND_M:
				if (paddingLeft) {
					createGround(parent, nextX - 180, Globals.groundM_y,
							spriteData.width + 180);
				} else {
					createGround(parent, nextX, Globals.groundM_y,
							spriteData.width);
				}
				lastGroundLeft = nextX;
				lastGroundTop = Globals.groundM_y;
				nextX += spriteData.width;
				levelWidth += spriteData.width;
				paddingLeft = true;
				break;
			case SpriteType.GROUND_L:
				if (paddingLeft) {
					createGround(parent, nextX - 180, Globals.groundL_y,
							spriteData.width + 180);
				} else {
					createGround(parent, nextX, Globals.groundL_y,
							spriteData.width);
				}
				lastGroundLeft = nextX;
				lastGroundTop = Globals.groundL_y;
				nextX += spriteData.width;
				levelWidth += spriteData.width;
				paddingLeft = true;
				break;
			case SpriteType.GROUND_H:
				if (paddingLeft) {
					createGround(parent, nextX - 180, Globals.groundH_y,
							spriteData.width + 180);
				} else {
					createGround(parent, nextX, Globals.groundH_y,
							spriteData.width);
				}
				lastGroundLeft = nextX;
				lastGroundTop = Globals.groundH_y;
				nextX += spriteData.width;
				levelWidth += spriteData.width;
				paddingLeft = true;
				break;
			case SpriteType.BOX:
				Box box = new Box();
				box.setPosition(lastGroundLeft + spriteData.rx, lastGroundTop
						- 4 * Globals.scale_ratio);
				parent.addChild(box);
				break;
			case SpriteType.BRIDGE:
				Bridge bridge = new Bridge();
				bridge.setPosition(nextX - (14 * Globals.scale_ratio),
						lastGroundTop - (20 * Globals.scale_ratio));
				parent.addChild(bridge);
				nextX += bridge.getBoundingWidth() - (30 * Globals.scale_ratio);
				levelWidth += (bridge.getBoundingWidth() - (30 * Globals.scale_ratio));
				paddingLeft = false;
				break;
			case SpriteType.DINORSAUR_1:
				Dinosaur1 dinosaur = new Dinosaur1();
				dinosaur.setPosition(lastGroundLeft + spriteData.rx,
						lastGroundTop - 4);
				parent.addChild(dinosaur);
				break;
			case SpriteType.DINORSAUR_2:
				Dinosaur2 dinosaur2 = new Dinosaur2();
				dinosaur2.setPosition(lastGroundLeft + spriteData.rx,
						lastGroundTop - 4);
				parent.addChild(dinosaur2);
				break;
			case SpriteType.DINORSAUR_3:
				Dinosaur3 dinosaur3 = new Dinosaur3();
				dinosaur3.setPosition(lastGroundLeft + spriteData.rx,
						lastGroundTop - 4);
				parent.addChild(dinosaur3);
				break;
			case SpriteType.FIRE:
				Fire fire = new Fire();
				fire.setPosition(lastGroundLeft + spriteData.rx,
						lastGroundTop - 8);
				parent.addChild(fire);
				break;
			case SpriteType.FLOWER:
				Flower flower = new Flower();
				flower.setPosition(lastGroundLeft + spriteData.rx,
						lastGroundTop - 4);
				parent.addChild(flower);
				break;
			case SpriteType.GAP:
				nextX += spriteData.width;
				levelWidth += spriteData.width;
				paddingLeft = false;
				break;
			case SpriteType.GO_SIGN:
				GoSign gosign = new GoSign();
				gosign.setPosition(lastGroundLeft + spriteData.rx,
						lastGroundTop - 4);
				parent.addChild(gosign);
				break;
			case SpriteType.STOP_SIGN:
				StopSign stopsign = new StopSign();
				stopsign.setPosition(lastGroundLeft + spriteData.rx,
						lastGroundTop - 4);
				parent.addChild(stopsign);
				break;
			case SpriteType.TRAP:
				Trap trap = new Trap();
				trap.setPosition(lastGroundLeft + spriteData.rx,
						lastGroundTop - 4);
				parent.addChild(trap);
				break;
			case SpriteType.STONE:
				Stone stone = new Stone();
				stone.setPosition(nextX, lastGroundTop);
				parent.addChild(stone);
				nextX += stone.getBoundingWidth();
				levelWidth += stone.getBoundingWidth();
				break;
			default:
				break;
			}
		}
	}

	private void createGround(CCNode parent, float left, float top, float width) {
		// add left ground
		GroundL leftGround = new GroundL();
		leftGround.setPosition(left, top);
		parent.addChild(leftGround);
		float leftWidth = leftGround.getBoundingWidth() - 1;
		float currentX = left + leftWidth;
		GroundR rightGround = new GroundR();
		float rightWidth = rightGround.getBoundingWidth() - 1;
		float maxX = currentX + width - leftWidth - rightWidth;
		// add middle grounds
		boolean flag = false;
		while (currentX < maxX) {
			GameSprite middle = null;
			if (flag) {
				middle = new GroundM1();
				flag = false;
			} else {
				middle = new GroundM2();
				flag = true;
			}
			middle.setPosition(currentX, top);
			parent.addChild(middle);
			currentX += middle.getBoundingWidth() - 1;
		}
		// add right ground
		rightGround.setPosition(maxX, top);
		parent.addChild(rightGround);
	}

	public float getLevelWidth() {
		return levelWidth;
	}
}