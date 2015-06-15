package ict.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ict.ICT;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class CreativeTabBlocks extends CreativeTabs
{
    public CreativeTabBlocks(int tabIndexID, String tabLabel)
    {
        super(tabIndexID, tabLabel);
    }

    @SideOnly(Side.CLIENT)
    @Override
    /**The itemID for the item to be displayed on the tab*/
    public Item getTabIconItem()
    {
        return Item.getItemFromBlock(ICT.ict);
    }

    @Override
    public String getTranslatedTabLabel()
    {
        return ICT.MOD_NAME + " Blocks";
    }
}