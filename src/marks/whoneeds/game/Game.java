package marks.whoneeds.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.paint.Color;
import marks.whoneeds.gui.setups.SetupGame;
import marks.whoneeds.gui.sprites.SpriteRect;

public class Game {
	
	public static final double ACC = 0.0112;
	public static final double INIT_VEL = 4.0;
	
	List<SpriteRect> rects;
	SpriteRect sel;
	
	boolean moving, movX;
	double velocity;
	SetupGame callback;
	
	public Game(SetupGame callback) {
		this.callback = callback;
		rects = new ArrayList<>();
		sel = null;
		moving = movX = false;
		velocity = 0;
	}
	
	public void setupRound(int id) {
		int numExp = 0;
		if (id == 1) numExp = 1;
		if (id == 2) numExp = 2;
		if (id == 3) numExp = 4;
		if (id == 4) numExp = 6;
		if (id == 5) numExp = 9;
		if (id == 6) numExp = 12;
		if (id == 7) numExp = 15;
		if (id == 8) numExp = 20;
		if (id == 9) numExp = 28;
		if (id == 10) numExp = 44;
		rects.clear();
		// fill board
		Random rand = new Random();
		rects.add(new SpriteRect(2, Color.BROWN, rand.nextDouble() * 600 + 150, rand.nextDouble() * 200 + 150, 300, 300));
		for (int i = 0; i < numExp; ++i) { // exp
			SpriteRect temp = sel = rects.get(rand.nextInt(rects.size()));
			boolean success = false;
			do {
				double TOTALMASS = sel.mass();
				if (TOTALMASS >= 144 + 144) {
					int dir = rand.nextInt(4), temp2 = dir;
					do {
						double LENGTH = -1;
						switch(dir) {
						case 0: LENGTH = sel.y1(); break;
						case 1: LENGTH = 1200 - sel.x2(); break;
						case 2: LENGTH = 800 - sel.y2(); break;
						case 3: LENGTH = sel.x1(); break;
						}
						double m1 = 0, m2 = 0, min1 = 144, max1 = sel.mass() - 144;
						while (max1 - min1 > 0) {
							m1 = rand.nextDouble() * (max1 - min1) + min1; 
							m2 = TOTALMASS - m1; 
							double r1 = Math.sqrt(m1) / 2, r2 = Math.sqrt(m2) / 2;
							double l1 = 0, l2 = 0, min2 = r1 + r2 + 12, max2 = LENGTH - r1 - r2;
							while (max2 - min2 > 0) {
								l2 = rand.nextDouble() * (max2 - min2) + min2;
								l1 = (0.5 / ACC) * Math.pow(m1 * Math.sqrt(INIT_VEL * INIT_VEL - 2 * ACC * (l2 - r1 - r2)) / (m1 + m2), 2) - m1 * (r1 + r2) / (m1 + m2);
								if (l1 + l2 < LENGTH - min2) {
									success = true;
									rects.remove(sel);
									switch(dir) {
									case 0: rects.add(new SpriteRect(2, Color.BROWN, sel.cenX() - r1, sel.cenY() - l1 - r1, r1 * 2, r1 * 2));
											rects.add(new SpriteRect(2, Color.BROWN, sel.cenX() - r2, sel.cenY() - l2 - r2, r2 * 2, r2 * 2)); break;
									case 1: rects.add(new SpriteRect(2, Color.BROWN, sel.cenX() + l1 - r1, sel.cenY() - r1, r1 * 2, r1 * 2));
											rects.add(new SpriteRect(2, Color.BROWN, sel.cenX() + l2 - r2, sel.cenY() - r2, r2 * 2, r2 * 2)); break;
									case 2: rects.add(new SpriteRect(2, Color.BROWN, sel.cenX() - r1, sel.cenY() + l1 - r1, r1 * 2, r1 * 2));
											rects.add(new SpriteRect(2, Color.BROWN, sel.cenX() - r2, sel.cenY() + l2 - r2, r2 * 2, r2 * 2)); break;
									case 3: rects.add(new SpriteRect(2, Color.BROWN, sel.cenX() - l1 - r1, sel.cenY() - r1, r1 * 2, r1 * 2));
											rects.add(new SpriteRect(2, Color.BROWN, sel.cenX() - l2 - r2, sel.cenY() - r2, r2 * 2, r2 * 2)); break;
									}
									break;
								} else max2 /= 2;
							}
							if (! success)
								max1 /= 2;
							else break;
						}
						if (success)
							break;
						if (++dir == 4) dir = 0;
					} while (temp2 != dir);
				}
				
				if (success)
					break;
				
				int wx = rects.indexOf(sel) + 1;
				if (wx == rects.size()) wx = 0;
				sel = rects.get(wx);
			} while (temp != sel);
			if (success)
				continue;
			System.out.println("ERROR - Failed generating all rectangles");
			break;
		}
	}
	
	private void combine(SpriteRect rect1, SpriteRect rect2) {
		double newMass = rect1.mass() + rect2.mass();
		velocity = velocity * sel.mass() / newMass;
		double x = (rect1.mass() * rect1.cenX() + rect2.mass() * rect2.cenX()) / newMass;
		double y = (rect1.mass() * rect1.cenY() + rect2.mass() * rect2.cenY()) / newMass;
		rects.remove(rect1);
		rects.remove(rect2);
		double width = Math.sqrt(newMass);
		SpriteRect toAdd = new SpriteRect(2, Color.BROWN, x - width/2, y - width/2, width, width);
		rects.add(toAdd);
		this.setsel(toAdd);
	}
	
	public void clickSelection(double x, double y) {
		if (moving)
			return;
		for (SpriteRect rect : rects)
			if (rect.overlaps(x, y))
				setsel(rect);
	}
	
	private void setsel(SpriteRect newSel) {
		sel.changeColor(Color.BROWN);
		(sel = newSel).changeColor(Color.BLUE);
	}
	
	public void moveSelection(int dir) {
		if (moving)
			return;
		if (dir == 0 || dir == 2)
			rects.sort(SpriteRect::sortVer);
		else rects.sort(SpriteRect::sortHor);
		int target = -1;
		if (dir == 2 || dir == 1) {
			target = rects.indexOf(sel) + 1;
			if (target >= rects.size())
				target = 0;
		} else {
			target = rects.indexOf(sel) - 1;
			if (target < 0)
				target = rects.size() - 1;
		}
		this.setsel(rects.get(target));
	}
	
	public void update() {
		if (moving) {
			if (movX)
				sel.translate(velocity, 0);
			else sel.translate(0, velocity);
			if (sel.isOffScreen())
				callback.call(0);
			for (int i = rects.size() - 1; i >= 0; --i)
				if (sel != rects.get(i) && sel.collides(rects.get(i)))
					{combine(sel, rects.get(i)); break;}
			boolean neg = velocity < 0;
			if (neg && (velocity += ACC) >= 0) {velocity = 0; moving = false; if (rects.size() == 1) callback.call(1); }
			if (! neg && (velocity -= ACC) <= 0) {velocity = 0; moving = false; if (rects.size() == 1) callback.call(1); }
		}
	}
	
	public void launch(int dir) {
		if (moving)
			return;
		moving = true;
		switch(dir) {
		case 0: movX = false; velocity = -INIT_VEL; break;
		case 1: movX = true; velocity = INIT_VEL; break;
		case 2: movX = false; velocity = INIT_VEL; break;
		case 3: movX = true; velocity = -INIT_VEL; break;
		}
	}
	
	public boolean accepting() {
		return ! moving;
	}
	
	public List<SpriteRect> getToDraw() {
		return rects;
	}

}
