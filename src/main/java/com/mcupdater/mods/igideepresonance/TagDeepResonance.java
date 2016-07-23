package com.mcupdater.mods.igideepresonance;

import com.github.lunatrius.ingameinfo.tag.Tag;
import com.github.lunatrius.ingameinfo.tag.registry.TagRegistry;
import mcjty.deepresonance.items.RadiationMonitorItem;
import net.minecraftforge.fml.common.FMLCommonHandler;

public abstract class TagDeepResonance extends Tag
{
	@Override
	public String getCategory() {
		return "deepresonance";
	}

	public static class DeepResonanceRadiation extends TagDeepResonance {
		@Override
		public String getValue() {
			try {
				RadiationMonitorItem.fetchRadiation(player);
				return String.valueOf(new Float(RadiationMonitorItem.radiationStrength).longValue());
			} catch (NullPointerException npe) {
				return "0";
			} catch (Throwable e) {
				log(this, e);
			}
			return "NaN";
		}
	}

	public static void register() {
		TagRegistry.INSTANCE.register(new DeepResonanceRadiation().setName("drradiation"));
	}

	public void log(Tag tag, Throwable ex) {
		FMLCommonHandler.instance().getFMLLogger().warn(IGIDeepResonance.metadata.modId + ":" + tag.getName(), ex);
	}
}
