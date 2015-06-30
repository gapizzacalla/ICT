package ict.block;

import ict.inventory.ContainerICT;
import ict.tileentity.TileEntityICT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

import java.util.Random;

public class BlockICT extends BlockContainerICT
{
    private static String name = "ict";
    private static int index = 0;
    private static boolean craftSuccess = false;

    public BlockICT(Material material)
    {
        super(material);
        blockIconName = "interactive_table";
        iconFrontName = "interactive_table";
        iconTopName = "interactive_table";
        guiID = 1;
        setBlockName(name);
        setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityICT();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!Keyboard.isCreated())
        {
            try
            {
                Keyboard.create();
            } catch (LWJGLException e)
            {
                e.printStackTrace();
            }
        }
        if (!world.isRemote)
        {
            TileEntityICT tileEntity = (TileEntityICT)world.getTileEntity(x, y, z);
            ItemStack heldItem = player.getHeldItem();
            if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) //clear table
            {
                ContainerICT container = new ContainerICT(player.inventory, world, x, y, z, tileEntity);
                for (int i = 0; i < 9; i++)
                {
                    ItemStack itemStack = tileEntity.getStackInSlot(i);
                    if (itemStack != null)
                    {
                        Entity item = new EntityItem(world, (double) x, (double) y, (double) z, itemStack);
                        world.spawnEntityInWorld(item);
                        tileEntity.setInventorySlotContents(i, null);
                    }
                }
                tileEntity.setInventorySlotContents(9, null);
                index = 0;
                container.detectAndSendChanges();
            }
            else if (!player.isSneaking()) //place item in table
            {
                ContainerICT container = new ContainerICT(player.inventory, world, x, y, z, tileEntity);
                if (heldItem != null)
                {
                    if (tileEntity.getStackInSlot(index) == null)
                    {
                        heldItem.stackSize--;
                        tileEntity.setInventorySlotContents(index, new ItemStack(heldItem.getItem(), 1, heldItem.getItemDamage()));
                        player.addChatComponentMessage(new ChatComponentText("Put " + tileEntity.getStackInSlot(index).getDisplayName() + " to slot " + String.valueOf(index + 1)));
                    }
                    else
                    {
                        player.addChatComponentMessage(new ChatComponentText("Slot contains " + tileEntity.getStackInSlot(index).getDisplayName()));
                    }
                    countIndex();
                }
                if (heldItem == null)
                {
                    player.addChatComponentMessage(new ChatComponentText("Skipping slot " + String.valueOf(index + 1)));
                    countIndex();
                }
                syncContainerWithTileEntity(container, tileEntity);
                container.detectAndSendChanges();
            }
            else //craft
            {
                ContainerICT container = new ContainerICT(player.inventory, world, x, y, z, tileEntity);
                syncContainerWithTileEntity(container, tileEntity);
                checkCraft(container, player);
                syncContainerWithTileEntity(container, tileEntity);
            }
        }
        if (craftSuccess)
        {
            Random random = new Random();
            float f = (float)x + 0.5F;
            float f1 = (float)y + 0.0F + random.nextFloat() * 6.0F / 16.0F;
            float f2 = (float)z + 0.5F;
            float f3 = 0.52F;
            float f4 = random.nextFloat() * 0.6F - 0.3F;
            world.spawnParticle("magicCrit", (double)(f - f3), (double)f1 + 0.5, (double)(f2 + f4), 0.0D, 0.5D, 0.0D);
            world.spawnParticle("magicCrit", (double)(f - f3), (double)f1 + 0.5, (double)(f2 + f4), 0.0D, 0.5D, 0.0D);

            world.spawnParticle("magicCrit", (double)(f + f3), (double)f1 + 0.5, (double)(f2 + f4), 0.0D, 0.5D, 0.0D);
            world.spawnParticle("magicCrit", (double)(f + f3), (double)f1 + 0.5, (double)(f2 + f4), 0.0D, 0.5D, 0.0D);

            world.spawnParticle("magicCrit", (double)(f + f4), (double)f1 + 0.5, (double)(f2 - f3), 0.0D, 0.5D, 0.0D);
            world.spawnParticle("magicCrit", (double)(f + f4), (double)f1 + 0.5, (double)(f2 - f3), 0.0D, 0.5D, 0.0D);

            world.spawnParticle("magicCrit", (double)(f + f4), (double)f1 + 0.5, (double)(f2 + f3), 0.0D, 0.5D, 0.0D);
            world.spawnParticle("magicCrit", (double)(f + f4), (double)f1 + 0.5, (double)(f2 + f3), 0.0D, 0.5D, 0.0D);
            craftSuccess = false;
        }
        return true;
    }

    @Override
    public void breakBlock(World world, int par2, int par3, int par4, Block block, int par6)
    {
        if (!world.isRemote)
        {
            TileEntityICT tileEntity = (TileEntityICT) world.getTileEntity(par2, par3, par4);
            BlockICT.index = 0;
            for (int i = 0; i < 9; i++)
            {
                ItemStack itemStack = tileEntity.getStackInSlot(i);
                if (itemStack != null)
                {
                    Entity item = new EntityItem(world, (double) par2, (double) par3, (double) par4, itemStack);
                    world.spawnEntityInWorld(item);
                    tileEntity.setInventorySlotContents(i, null);
                }
            }
            tileEntity.setInventorySlotContents(9, null);
        }
        super.breakBlock(world, par2, par3, par4, block, par6);
    }

    public String getName()
    {
        return name;
    }

    private void countIndex()
    {
        if (index == 8)
        {
            index = 0;
        } else
        {
            index++;
        }
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    private void checkCraft(ContainerICT container, EntityPlayer player)
    {
        ItemStack craftedItem = container.slotClick(0, 0, 0, player);
        if (craftedItem != null)
        {
            index = 0;
            player.addChatComponentMessage(new ChatComponentText("Crafted: " + craftedItem.getDisplayName()));
            player.inventoryContainer.detectAndSendChanges();
            craftSuccess = true;
        }
        else
        {
            player.addChatComponentMessage(new ChatComponentText("Recipe not found!"));
        }
        container.detectAndSendChanges();
    }

    private void syncContainerWithTileEntity(ContainerICT container, TileEntityICT tileEntity)
    {
        for (int i = 0; i < 9; i++)
        {
            container.craftMatrix.setInventorySlotContents(i, tileEntity.getStackInSlot(i));
        }
    }
}