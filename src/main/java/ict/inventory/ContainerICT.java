package ict.inventory;

import ict.ICT;
import ict.tileentity.TileEntityICT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

public class ContainerICT extends Container
{
    /** The crafting matrix inventory (3x3) */
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    public IInventory craftResult = new InventoryCraftResult();
    private World worldObj;
    private int x;
    private int y;
    private int z;
    private TileEntityICT tileEntity;

    public ContainerICT(InventoryPlayer player, World world, int x, int y, int z, TileEntityICT tileEntity)
    {
        this.worldObj = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.tileEntity = tileEntity;
        int i;
        int j;
        /** Crafting Result 0*/
        this.addSlotToContainer(new SlotCrafting(player.player, this.tileEntity, this.craftResult, 0, 124, 35));
        /** Crafting Matrix 1-9*/
        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 3; ++j)
            {
                this.addSlotToContainer(new Slot(this.tileEntity, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }
        /** Player Inventory 10-36*/
        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        /** Hot Bar 37-45*/
        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
        }
        this.detectAndSendChanges();
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(IInventory inventory)
    {
        ItemStack itemStack = CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj);
        this.craftResult.setInventorySlotContents(0, itemStack);
        this.tileEntity.setInventorySlotContents(9, itemStack);
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);
    }

    public boolean canInteractWith(EntityPlayer player)
    {
        return this.worldObj.getBlock(this.x, this.y, this.z) == ICT.ict && player.getDistanceSq((double) this.x + 0.5D, (double) this.y + 0.5D,
                (double) this.z + 0.5D) <= 64.0D;
    }

    /**
     * @param index the ID for the Slot clicked. Matches with Slot.slotNumber
     * @param button the button pressed. For mouse clicks: 0 is left click, 1 is right click.
     * @param flag usually, 0 is the regular behaviour, 1 is shift-clicking, 2 is keyboard input.
     */
    @Override
    public ItemStack slotClick(int index, int button, int flag, EntityPlayer player)
    {
        InventoryPlayer inventoryplayer = player.inventory;
        Slot slot;
        ItemStack itemStack = null;
        ItemStack itemStack2;
        ItemStack itemStack3;
        int i;
        if ((flag == 0 || flag == 1) && (button == 0 || button == 1))
        {
            if (index == -999)
            {
                if (inventoryplayer.getItemStack() != null)
                {
                    if (button == 0)
                    {
                        player.dropPlayerItemWithRandomChoice(inventoryplayer.getItemStack(), true);
                        inventoryplayer.setItemStack(null);
                    }
                    if (button == 1)
                    {
                        player.dropPlayerItemWithRandomChoice(inventoryplayer.getItemStack().splitStack(1), true);

                        if (inventoryplayer.getItemStack().stackSize == 0)
                        {
                            inventoryplayer.setItemStack(null);
                        }
                    }
                }
            }
            else if (flag == 1)
            {
                if (index < 0)
                {
                    return null;
                }
                slot = (Slot)this.inventorySlots.get(index);
                if (slot != null && slot.canTakeStack(player))
                {
                    itemStack2 = this.transferStackInSlot(player, index);
                    if (itemStack2 != null)
                    {
                        Item item = itemStack2.getItem();
                        itemStack = itemStack2.copy();
                        if (slot.getStack() != null && slot.getStack().getItem() == item)
                        {
                            this.retrySlotClick(index, button, true, player);
                        }
                    }
                }
            }
            else
            {
                if (index < 0)
                {
                    return null;
                }
                slot = (Slot)this.inventorySlots.get(index);
                if (slot != null)
                {
                    itemStack2 = slot.getStack();
                    ItemStack itemstack4 = inventoryplayer.getItemStack();
                    if (itemStack2 != null)
                    {
                        itemStack = itemStack2.copy();
                    }
                    if (itemStack2 == null)
                    {
                        if (itemstack4 != null && slot.isItemValid(itemstack4))
                        {
                            i = button == 0 ? itemstack4.stackSize : 1;

                            if (i > slot.getSlotStackLimit())
                            {
                                i = slot.getSlotStackLimit();
                            }
                            if (itemstack4.stackSize >= i)
                            {
                                slot.putStack(itemstack4.splitStack(i));
                            }
                            if (itemstack4.stackSize == 0)
                            {
                                inventoryplayer.setItemStack(null);
                            }
                        }
                    }
                    else if (slot.canTakeStack(player))
                    {
                        if (itemstack4 == null)
                        {
                            i = button == 0 ? itemStack2.stackSize : (itemStack2.stackSize + 1) / 2;
                            itemStack3 = slot.decrStackSize(i);
                            inventoryplayer.setItemStack(itemStack3);
                            if (itemStack2.stackSize == 0)
                            {
                                slot.putStack(null);
                            }
                            slot.onPickupFromSlot(player, inventoryplayer.getItemStack());
                        }
                        else if (slot.isItemValid(itemstack4))
                        {
                            if (itemStack2.getItem() == itemstack4.getItem() && itemStack2.getItemDamage() == itemstack4.getItemDamage() && ItemStack.areItemStackTagsEqual(itemStack2, itemstack4))
                            {
                                i = button == 0 ? itemstack4.stackSize : 1;
                                if (i > slot.getSlotStackLimit() - itemStack2.stackSize)
                                {
                                    i = slot.getSlotStackLimit() - itemStack2.stackSize;
                                }
                                if (i > itemstack4.getMaxStackSize() - itemStack2.stackSize)
                                {
                                    i = itemstack4.getMaxStackSize() - itemStack2.stackSize;
                                }
                                itemstack4.splitStack(i);
                                if (itemstack4.stackSize == 0)
                                {
                                    inventoryplayer.setItemStack(null);
                                }
                                itemStack2.stackSize += i;
                            }
                            else if (itemstack4.stackSize <= slot.getSlotStackLimit())
                            {
                                slot.putStack(itemstack4);
                                inventoryplayer.setItemStack(itemStack2);
                            }
                        }
                        else if (itemStack2.getItem() == itemstack4.getItem() && itemstack4.getMaxStackSize() > 1 && (!itemStack2.getHasSubtypes() || itemStack2.getItemDamage() == itemstack4.getItemDamage()) && ItemStack.areItemStackTagsEqual(itemStack2, itemstack4))
                        {
                            i = itemStack2.stackSize;
                            if (i > 0 && i + itemstack4.stackSize <= itemstack4.getMaxStackSize())
                            {
                                itemstack4.stackSize += i;
                                itemStack2 = slot.decrStackSize(i);
                                if (itemStack2.stackSize == 0)
                                {
                                    slot.putStack(null);
                                }
                                slot.onPickupFromSlot(player, inventoryplayer.getItemStack());
                            }
                        }
                    }
                    slot.onSlotChanged();
                }
            }
        }
        if (itemStack != null)
        {
            if (!this.mergeItemStack(itemStack, 37, 45, false))
            {
                this.mergeItemStack(itemStack, 10, 36, false);
            }
            inventoryplayer.setItemStack(null);
        }
        player.inventoryContainer.detectAndSendChanges();
        this.detectAndSendChanges();
        return itemStack;
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        return super.transferStackInSlot(player, index);
    }

    public boolean func_94530_a(ItemStack itemStack, Slot slot)
    {
        return slot.inventory != this.craftResult && super.func_94530_a(itemStack, slot);
    }
}