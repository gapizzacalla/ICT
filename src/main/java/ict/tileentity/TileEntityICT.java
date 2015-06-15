package ict.tileentity;

import ict.ICT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityICT extends TileEntity implements ISidedInventory
{
    private static String localizedName;
    private static final int[] output = new int[]{0}, matrix = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    private ItemStack[] slots = new ItemStack[10];

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        switch (side)
        {
            case 0:
                return output;
            default:
                return matrix;
        }
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int side)
    {
        return isItemValidForSlot(slot, itemStack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, int side)
    {
        return false;
    }

    @Override
    public int getSizeInventory()
    {
        return slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return slots[index];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null)
        {
            if (itemStack.stackSize <= amount)
            {
                setInventorySlotContents(slot, null);
            }
            else
            {
                itemStack = itemStack.splitStack(amount);
                markDirty();
            }
        }
        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        ItemStack itemStack = getStackInSlot(slot);
        setInventorySlotContents(slot, null);
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        slots[slot] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
            itemStack.stackSize = getInventoryStackLimit();
        markDirty();
    }

    @Override
    public String getInventoryName()
    {
        return hasCustomInventoryName() ? localizedName : "container.ict";
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return localizedName != null && localizedName.length() > 0;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer)
    {
        return this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord) == ICT.ict && entityPlayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
                (double) this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        NBTTagList nbtTagList = nbtTagCompound.getTagList("items", 10);
        this.slots = new ItemStack[getSizeInventory()];
        for (int i = 0; i < nbtTagList.tagCount(); i++)
        {
            NBTTagCompound nbtTagCompound2 = nbtTagList.getCompoundTagAt(i);
            int item = nbtTagCompound2.getInteger("slot");
            if (item >= 0 && item < this.slots.length)
            {
                this.slots[item] = ItemStack.loadItemStackFromNBT(nbtTagCompound2);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        NBTTagList nbtTagList = new NBTTagList();
        for (int i = 0; i < this.slots.length; i++)
        {
            if (this.slots[i] != null)
            {
                NBTTagCompound nbtTagCompund1 = new NBTTagCompound();
                nbtTagCompund1.setInteger("slot", i);
                this.slots[i].writeToNBT(nbtTagCompund1);
                nbtTagList.appendTag(nbtTagCompund1);
            }
        }
        nbtTagCompound.setTag("items", nbtTagList);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbtTag = new NBTTagCompound();
        writeToNBT(nbtTag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet)
    {
        readFromNBT(packet.func_148857_g());
    }

    @Override
    public void updateEntity()
    {

    }

    public void setGuiDisplayName(String displayName)
    {
        localizedName = displayName;
    }
}