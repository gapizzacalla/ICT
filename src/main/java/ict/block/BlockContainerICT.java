package ict.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ict.reference.Names;
import ict.tileentity.TileEntityICT;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class BlockContainerICT extends BlockContainer implements ITileEntityProvider
{
    protected String blockIconName = "", iconFrontName = "", iconTopName = "";
    protected byte guiID;

    @SideOnly(Side.CLIENT)
    protected IIcon iconTop;
    protected IIcon iconFront;

    public BlockContainerICT(Material material)
    {
        super(material);
        setHardness(3.0F);
        setResistance(5.0F);
        setStepSound(getStepSoundType(material));
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(Names.RESOURCE_PREFIX + blockIconName);
        iconFront = iconRegister.registerIcon(Names.RESOURCE_PREFIX + iconFrontName);
        iconTop = iconRegister.registerIcon(Names.RESOURCE_PREFIX + iconTopName);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        return side == 1 ? this.iconTop : (side == 0 ? this.iconTop : (side != metadata ? this.blockIcon : this.iconFront));
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public Item getItemDropped(int i, Random random, int j)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        setDefaultDirection(world, x, y, z);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack)
    {
        int l = MathHelper.floor_double((double) (entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        if (l == 0)
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        if (l == 1)
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        if (l == 2)
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        if (l == 3)
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        if (itemStack.hasDisplayName())
            ((TileEntityICT) world.getTileEntity(x, y, z)).setGuiDisplayName(itemStack.getDisplayName());
    }

    @Override
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    @Override
    public int getComparatorInputOverride(World world, int x, int y, int z, int i)
    {
        return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(x, y, z));
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Names.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    private String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    private void setDefaultDirection(World world, int x, int y, int z)
    {
        if (!world.isRemote)
        {
            Block block1 = world.getBlock(x, y, z - 1);
            Block block2 = world.getBlock(x, y, z + 1);
            Block block3 = world.getBlock(x - 1, y, z);
            Block block4 = world.getBlock(x + 1, y, z);
            byte b0 = 3;
            if (block1.func_149730_j() && !block2.func_149730_j())
                b0 = 3;
            if (block2.func_149730_j() && !block1.func_149730_j())
                b0 = 2;
            if (block3.func_149730_j() && !block4.func_149730_j())
                b0 = 5;
            if (block4.func_149730_j() && !block3.func_149730_j())
                b0 = 4;
            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

    private SoundType getStepSoundType(Material material)
    {
        if (material == Material.cloth)
            return soundTypeCloth;
        else if (material == Material.iron)
            return soundTypeMetal;
        else if (material == Material.rock)
            return soundTypeStone;
        else
            return soundTypeStone;
    }
}