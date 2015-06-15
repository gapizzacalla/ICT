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

public class BlockICT extends BlockContainerICT
{
    private static String name = "ict";
    private static int index = 0;
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
                for (int k = 0; k < 9; k++)
                {
                    if (container.craftMatrix.getStackInSlot(k) != null)
                    {
                        System.out.println(container.craftMatrix.getStackInSlot(k).getDisplayName());
                    }
                }
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
                for (int i = 0; i < 9; i++)
                {
                    container.craftMatrix.setInventorySlotContents(i, tileEntity.getStackInSlot(i));
                }
                container.detectAndSendChanges();
            }
            else //craft
            {
                ContainerICT container = new ContainerICT(player.inventory, world, x, y, z, tileEntity);
                for (int i = 0; i < 9; i++)
                {
                    container.craftMatrix.setInventorySlotContents(i, tileEntity.getStackInSlot(i));
                }
                checkCraft(container, player);
            }
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

    private void checkCraft(ContainerICT container, EntityPlayer player)
    {
        ItemStack craftedItem = container.slotClick(0, 0, 0, player);
        if (craftedItem != null)
        {
            index = 0;
            player.addChatComponentMessage(new ChatComponentText("Crafted: " + craftedItem.getDisplayName()));
            player.inventoryContainer.detectAndSendChanges();
        }
        else
        {
            player.addChatComponentMessage(new ChatComponentText("Recipe not found!"));
        }
        container.detectAndSendChanges();
    }
}