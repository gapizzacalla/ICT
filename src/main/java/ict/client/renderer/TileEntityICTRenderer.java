package ict.client.renderer;

import ict.ICT;
import ict.model.ModelICT;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityICTRenderer extends TileEntitySpecialRenderer
{
    private static final ResourceLocation texture = new ResourceLocation(ICT.MOD_ID, "/textures/models/ICT.png");
    private ModelICT model;

    public TileEntityICTRenderer()
    {
        this.model = new ModelICT();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glRotatef(180, 0F, 0F, 1F);
        this.bindTexture(texture);
        GL11.glPushMatrix();
        this.model.renderModel(0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}