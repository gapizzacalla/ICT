package ict.helper;

import ict.ICT;
import ict.tileentity.TileEntityICT;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderHelper implements IItemRenderer
{
    @Override
    public boolean handleRenderType(ItemStack itemStack, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack itemStack, ItemRendererHelper helper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack itemStack, Object... data)
    {
        if (itemStack.getDisplayName().equals(new ItemStack(ICT.ict, 1).getDisplayName()))
        {
            TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityICT(), 0.0D, 0.0D, 0.0D, 1.0F);
        }
    }

    public static void render(int index, int offset, ItemStack itemStack)
    {
        if (itemStack != null)
        {
            if (itemStack.getItem() instanceof ItemBlock)
            {
                //Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
                //drawTextureFromIcon(index * 18, offset * 18, itemStack.getIconIndex(), 16, 16);
            }
            else
            {
                Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
                drawTextureFromIcon(index * 18, offset * 18, itemStack.getIconIndex(), 16, 16);
            }
        }
        itemStack = null;
    }

    public static void drawTextureFromIcon(int p_94065_1_, int p_94065_2_, IIcon p_94065_3_, int p_94065_4_, int p_94065_5_)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(p_94065_1_), (double)(p_94065_2_ + p_94065_5_), 0, (double)p_94065_3_.getMinU(), (double)p_94065_3_.getMaxV());
        tessellator.addVertexWithUV((double)(p_94065_1_ + p_94065_4_), (double)(p_94065_2_ + p_94065_5_), 0, (double)p_94065_3_.getMaxU(), (double)p_94065_3_.getMaxV());
        tessellator.addVertexWithUV((double)(p_94065_1_ + p_94065_4_), (double)(p_94065_2_), 0, (double)p_94065_3_.getMaxU(), (double)p_94065_3_.getMinV());
        tessellator.addVertexWithUV((double)(p_94065_1_), (double)(p_94065_2_), 0, (double)p_94065_3_.getMinU(), (double)p_94065_3_.getMinV());
        tessellator.draw();
    }
}