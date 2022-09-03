package com.shynieke.georenouveau.entity.serializer;

import com.shynieke.georenouveau.entity.LinkedGeOre;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;

public class LinkedGeOreSerializer implements EntityDataSerializer<LinkedGeOre> {

	@Override
	public void write(FriendlyByteBuf buf, LinkedGeOre value) {
		buf.writeByte(value.ordinal());
	}

	@Override
	public LinkedGeOre read(FriendlyByteBuf buf) {
		return LinkedGeOre.values()[buf.readByte()];
	}

	@Override
	public EntityDataAccessor<LinkedGeOre> createAccessor(int id) {
		return new EntityDataAccessor<>(id, this);
	}

	@Override
	public LinkedGeOre copy(LinkedGeOre value) {
		return value;
	}
}
