package ict.client.config;

import cpw.mods.fml.client.config.GuiConfig;
import ict.ICT;
import ict.handler.ConfigurationHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class GuiConfiguration extends GuiConfig
{
    public GuiConfiguration(GuiScreen guiScreen)
    {
        super(guiScreen, new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL))
                .getChildElements(), ICT.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(ICT.CONFIG_NAME));
    }
}