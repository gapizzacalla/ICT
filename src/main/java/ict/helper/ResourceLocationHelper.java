package ict.helper;

import ict.ICT;
import net.minecraft.util.ResourceLocation;

/**
 * @author pahimar
 */
public class ResourceLocationHelper
{
    public static ResourceLocation getResourceLocation(String path)
    {
        return new ResourceLocation(ICT.MOD_ID.toLowerCase(), path);
    }
}