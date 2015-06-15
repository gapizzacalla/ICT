package ict.event;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import ict.ICT;
import ict.helper.RenderHelper;
import ict.tileentity.TileEntityICT;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;

public class EventsHandler
{
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void placeEvent(PlaceEvent event)
    {
        Block block1 = event.placedAgainst;
        Block block2 = event.placedBlock;
        if (block1 == Blocks.crafting_table && block2 == Blocks.carpet)
        {
            event.world.setBlockToAir(event.x, event.y, event.z);
            event.world.setBlockToAir(event.x, event.y - 1, event.z);
            event.world.setBlock(event.x, event.y - 1, event.z, ICT.ict);
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void renderGameOverlay(RenderGameOverlayEvent.Post event)
    {
        Minecraft mc = Minecraft.getMinecraft();
        World world = mc.theWorld;
        if (event.type == RenderGameOverlayEvent.ElementType.ALL)
        {
            MovingObjectPosition mop = mc.objectMouseOver;
            if (mop != null)
            {
                Block block = world.getBlock(mop.blockX, mop.blockY, mop.blockZ);
                TileEntityICT tileEntity = (TileEntityICT) world.getTileEntity(mop.blockX, mop.blockY, mop.blockZ);
                if (block.getLocalizedName().equals(ICT.ict.getLocalizedName()))
                {
                    if (tileEntity != null)
                    {
                        //Load ItemStacks from TileEntity
                        ItemStack[] itemStacks = new ItemStack[10];
                        for (int i = 0; i < 10; i++)
                        {
                            itemStacks[i] = tileEntity.getStackInSlot(i);
                        }
                        //Render each slot as a grid
                        int i, j, k;
                        for (i = 0; i < 3; i++)
                        {
                            RenderHelper.render(i, 1, itemStacks[i]);
                        }
                        for (j = 3; j < 6; j++)
                        {
                            RenderHelper.render(j - 3, 2, itemStacks[j]);
                        }
                        for (k = 6; k < 9; k++)
                        {
                            RenderHelper.render(k - 6, 3, itemStacks[k]);
                        }
                        //Render output
                        RenderHelper.render(4, 2, itemStacks[9]);
                    }
                }
            }
        }
    }
}