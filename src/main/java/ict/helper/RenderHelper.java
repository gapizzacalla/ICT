package ict.helper;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

public class RenderHelper
{
    public static void render(int index, int offset, ItemStack itemStack)
    {
        if (itemStack != null)
        {
            if (itemStack.getItem() instanceof ItemBlock)
            {
                Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
                drawTextureFromIcon(index * 18, offset * 18, itemStack.getIconIndex(), 16, 16);
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
        tessellator.addVertexWithUV((double)(p_94065_1_ + 0), (double)(p_94065_2_ + p_94065_5_), 0, (double)p_94065_3_.getMinU(), (double)p_94065_3_.getMaxV());
        tessellator.addVertexWithUV((double)(p_94065_1_ + p_94065_4_), (double)(p_94065_2_ + p_94065_5_), 0, (double)p_94065_3_.getMaxU(), (double)p_94065_3_.getMaxV());
        tessellator.addVertexWithUV((double)(p_94065_1_ + p_94065_4_), (double)(p_94065_2_ + 0), 0, (double)p_94065_3_.getMaxU(), (double)p_94065_3_.getMinV());
        tessellator.addVertexWithUV((double)(p_94065_1_ + 0), (double)(p_94065_2_ + 0), 0, (double)p_94065_3_.getMinU(), (double)p_94065_3_.getMinV());
        tessellator.draw();
    }
}