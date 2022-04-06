import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ToolTipManager;
class Model
{
	Coin coin;
	Mario mario;
	ArrayList<Sprite> sprites;
	
	Model()
	{
		sprites = new ArrayList<Sprite>();
		mario = new Mario();
		sprites.add(mario);
		
		Json j = Json.load("map.json");
		this.unmarshal(j);
	}

	public void update()
	{
		Iterator<Sprite> iter = sprites.iterator();
		while(iter.hasNext())
		{
			Sprite s = iter.next();
			s.update();
		}
		
		for(int i = 1; i < sprites.size(); i++)
		{
			checkCollisions(mario, sprites.get(i));
		}		
		CoinDeleter();
		
	}

	public void checkCollisions(Sprite collider1, Sprite collider2)

	{
		for(int i = 1; i < sprites.size(); i++)
		{
			if(sprites.get(i).isBrick())
			{
			Sprite s = sprites.get(i);
			
		int Collider1Left = collider1.x;
		int Collider1Right = collider1.x + collider1.w;
		int Collider2Left = s.x;
		int Collider2Right = s.x + s.w;

		int Collider1Top = collider1.y;
		int Collider1Bot = collider1.y + collider1.h;
		int Collider2Top = s.y;
		int Collider2Bot = s.y + s.h;

		boolean Collision;	
		Collision = false;
		boolean CollisionTop;
		boolean CollisionBot;
		boolean CollisionRight;
		boolean CollisionLeft;

		boolean CornerRightTop;
		boolean CornerRightBot;
		boolean CornerLeftTop;
		boolean CornerLeftBot;

		float int_x;
		float int_y;

		//Check for ANY Collisions:
		if (Collider1Right < Collider2Left || Collider1Left > Collider2Right || Collider1Bot < Collider2Top || Collider1Top > Collider2Bot)
		{	
			Collision = false;
			//System.out.println("COLLISION IS FALSE");
		}
		else
		{
			Collision = true;
			//System.out.println("COLLISION IS TRUE");
		}
		//Check for SIDE Collisions:
		if(Collision)
		{
			//System.out.println("I'm Colliding");

			if(CollisionTop = (Collider1Bot >= Collider2Top && Collider1Top <= Collider2Top))
			{
				CollisionTop = true;
			}
			else
			{
				CollisionTop = false;
			}

			if(CollisionBot = (Collider1Top <= Collider2Bot && Collider1Bot >= Collider2Bot))
			{
				CollisionBot = true;
			}
			else
			{
				CollisionBot = false;
			}
			

			if(CollisionRight = (Collider1Left < Collider2Right && Collider1Right >= Collider2Right))
			{
				CollisionRight = true;
			}
			else
			{
				CollisionRight = false;
			}

			if(CollisionLeft = (Collider1Right > Collider2Left && Collider1Left <= Collider2Left))
			{
				CollisionLeft = true;
			}
			else
			{
				CollisionLeft = false;
			}

			//Corner Collisions
			if(CornerRightTop = CollisionRight && CollisionTop)
			{
				CornerRightTop = true;
			}
			else
			{
				CornerRightTop = false;
			}

			if(CornerRightBot = CollisionRight && CollisionBot)
			{
				CornerRightBot = true;
			}
			else
			{
				CornerRightBot = false;
			}

			if(CornerLeftTop = CollisionLeft && CollisionTop)
			{
				CornerLeftTop = true;
			} 
			else
			{
				CornerLeftTop = false;
			}

			if(CornerLeftBot = CollisionLeft && CollisionBot)
			{
				CornerLeftBot = true;
			} 
			else
			{
				CornerLeftBot = false;
			}

			// Corner Collision AND Round up Boundaries (DETERMINES IF MARIO CLOSER TO TOP/BOT OR RIGHT/LEFT OF BLOCK)
			if(CollisionRight && (Collider1Bot < Collider2Bot && Collider1Top > Collider2Top))
			{
				CollisionRight = true;
			}
			
			else if(CollisionLeft && (Collider1Bot < Collider2Bot && Collider1Top > Collider2Top))
			{
				CollisionLeft = true;
			}

			else if(CollisionTop && (Collider1Left > Collider2Left && Collider1Right < Collider2Right))
			{
				CollisionTop = true;
			}

			else if(CollisionBot && (Collider1Left > Collider2Left && Collider1Right < Collider2Right))
			{
				CollisionBot = true;
			}

			else if(CornerRightBot)
			{
				int_x = Collider2Right - Collider1Left;
				int_y = Collider2Bot - Collider1Top;
				CollisionRight = (int_x < int_y);
				CollisionBot = (int_x >= int_y);
			}

			else if(CornerLeftBot)
			{
				int_x = Math.abs(Collider2Left - Collider1Right);
				int_y = Collider2Bot - Collider1Top;
				CollisionLeft = (int_x < int_y);
				CollisionBot = (int_x >= int_y);
			}

			else if(CornerRightTop)
			{
				int_x = Collider2Right - Collider1Left;
				int_y = Math.abs(Collider2Top - Collider1Bot);
				CollisionRight = (int_x < int_y);
				CollisionTop = (int_x >= int_y);
			}

			else if(CornerLeftTop)
			{
				int_x = Math.abs(Collider2Left - Collider1Right);
				int_y = Math.abs(Collider2Top - Collider1Bot);
				CollisionLeft = (int_x < int_y);
				CollisionTop = (int_x >= int_y);
			}
			else
			{
				CollisionRight = false;
				CollisionLeft = false;
				CollisionBot = false;
				CollisionTop = false;
			}

			//ACTUAL COLLIISION MATH / (GET OUT OF BLOCK CONDITIONAL STATEMENTS  AND MATH)
			if(CollisionLeft)
			{
				collider1.x = Collider2Left - collider1.w;
			}
			
			if(CollisionRight)
			{
				collider1.x = Collider2Right;
			}

			if(CollisionTop)
			{
				collider1.y = Collider2Top - collider1.h;
				mario.vert_velocity = 0;
				mario.CounterJ = 0;
			}
			if(CollisionBot)
			{
				collider1.y = Collider2Bot;
				if(s.totalc > 0 && s.type == 1)
				 {
					sprites.add(new Coin(s.x, s.y, s.w, s.h));
					s.totalc--;
				 }
				mario.vert_velocity += 4.1;
			}
		}

	}//IsBrick()
	}//For Loop
}//Function

	public void CoinDeleter()
	{
		for(int i = 0; i < sprites.size(); i++)
		{
			Sprite s = sprites.get(i);

			if(s.isCoin() == true && s.y >= 600)
			{
				sprites.remove(i);
			}

		}
	}

	public void setDestination(int x, int y)
	{
	}

	Json marshal()
    {
         Json ob = Json.newObject();
         Json tmpList = Json.newList();
         ob.add("bricks", tmpList);
         for(int i = 0; i < sprites.size(); i++)
		 if(sprites.get(i).isBrick())
             tmpList.add(sprites.get(i).marshal());
         return ob;
    }

     void unmarshal(Json ob) 
	{
		// sprites = new ArrayList<Sprite>();
		// mario = new Mario();
		// sprites.add(mario);
		Json tmpList = ob.get("bricks");
		for(int i = 0; i < tmpList.size(); i++)
			sprites.add(new Brick(tmpList.get(i), this));
	}
}