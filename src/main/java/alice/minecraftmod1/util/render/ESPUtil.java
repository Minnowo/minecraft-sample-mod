package alice.minecraftmod1.util.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.EXTPackedDepthStencil;
import org.lwjgl.opengl.GL11;

import alice.minecraftmod1.module.modules.render.VirtualBlock;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class ESPUtil 
{

	
	public static void beginRenderHitbox(float lineWidth, Color c) 
	{
		beginRenderHitbox();
		GL11.glLineWidth(lineWidth);
		ESPUtil.setColor(c);
	}
	
	public static void beginRenderHitbox(float lineWidth) 
	{
		beginRenderHitbox();
		GL11.glLineWidth(lineWidth);
	}
	
	public static void beginRenderHitbox() 
	{
		GL11.glPushMatrix();
        
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDepthMask(false);        
	}
	
	public static void endRenderHitbox() 
	{
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        
        GL11.glDepthMask(true);
        GL11.glPopMatrix();	
	}
	
    public static void renderEntityHitbox(Entity e) 
    {
    	double renderPosX = Minecraft.getMinecraft().getRenderManager().viewerPosX;
		double renderPosY = Minecraft.getMinecraft().getRenderManager().viewerPosY;
		double renderPosZ = Minecraft.getMinecraft().getRenderManager().viewerPosZ;
        
		double hw = e.width / 2;
		
    	double x1 = e.posX + hw - renderPosX;
		double x2 = e.posX - hw - renderPosX;
		
		double y1 = e.posY            - renderPosY;
		double y2 = e.posY + e.height - renderPosY;
		
		double z1 = e.posZ + hw - renderPosZ;
		double z2 = e.posZ - hw - renderPosZ;
		
		GL11.glBegin(2);
		
		GL11.glVertex3d(x1, y1, z1);
		GL11.glVertex3d(x2, y1, z1);
		GL11.glVertex3d(x2, y1, z2);
		GL11.glVertex3d(x1, y1, z2);
		
		GL11.glEnd();
		
		GL11.glBegin(1);
		
		GL11.glVertex3d(x1, y1, z1);
		GL11.glVertex3d(x1, y2, z1);
		
		GL11.glVertex3d(x1, y1, z2);
		GL11.glVertex3d(x1, y2, z2);
		
		GL11.glVertex3d(x2, y1, z1);
		GL11.glVertex3d(x2, y2, z1);
		
		GL11.glVertex3d(x2, y1, z2);
		GL11.glVertex3d(x2, y2, z2);
		
		GL11.glEnd();
		
		GL11.glBegin(2);
		GL11.glVertex3d(x1, y2, z1);
		GL11.glVertex3d(x2, y2, z1);
		GL11.glVertex3d(x2, y2, z2);
		GL11.glVertex3d(x1, y2, z2);
		
		GL11.glEnd();
    }

    
    public static void renderBlock(int x, int y, int z, VirtualBlock block) 
    {
    	GL11.glColor4ub((byte) block.r, (byte) block.g, (byte) block.b, (byte) block.a);

        GL11.glVertex3f(x, y, z);
        GL11.glVertex3f(x + 1, y, z);

        GL11.glVertex3f(x + 1, y, z);
        GL11.glVertex3f(x + 1, y, z + 1);

        GL11.glVertex3f(x, y, z);
        GL11.glVertex3f(x, y, z + 1);

        GL11.glVertex3f(x, y, z + 1);
        GL11.glVertex3f(x + 1, y, z + 1);

        GL11.glVertex3f(x, y + 1, z);
        GL11.glVertex3f(x + 1, y + 1, z);

        GL11.glVertex3f(x + 1, y + 1, z);
        GL11.glVertex3f(x + 1, y + 1, z + 1);

        GL11.glVertex3f(x, y + 1, z);
        GL11.glVertex3f(x, y + 1, z + 1);

        GL11.glVertex3f(x, y + 1, z + 1);
        GL11.glVertex3f(x + 1, y + 1, z + 1);

        GL11.glVertex3f(x, y, z);
        GL11.glVertex3f(x, y + 1, z);

        GL11.glVertex3f(x, y, z + 1);
        GL11.glVertex3f(x, y + 1, z + 1);

        GL11.glVertex3f(x + 1, y, z);
        GL11.glVertex3f(x + 1, y + 1, z);

        GL11.glVertex3f(x + 1, y, z + 1);
        GL11.glVertex3f(x + 1, y + 1, z + 1);
    }
    
    
    public static void setColor(Color c) 
    {
        glColor4d(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f, c.getAlpha() / 255f);
    }

    public static Color getHealthColor(float health) 
    {
        if (health <= 4)
            return new Color(200, 0, 0);
        
        if (health <= 8)
            return new Color(231, 143, 85);
        
        if (health <= 12)
            return new Color(219, 201, 106);
        
        if (health <= 16)
            return new Color(117, 231, 85);
        
        return new Color(44, 186, 19);
    }
}