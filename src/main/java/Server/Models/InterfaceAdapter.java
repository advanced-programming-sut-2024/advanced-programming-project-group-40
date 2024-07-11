package Server.Models;

import com.google.gson.*;
import enums.cards.CardInfo;
import enums.cards.HeroInfo;
import enums.cards.SpecialCardInfo;
import enums.cards.UnitCardInfo;

import java.lang.reflect.Type;

public class InterfaceAdapter implements JsonSerializer<CardInfo>, JsonDeserializer<CardInfo> {

    @Override
    public CardInfo deserialize(JsonElement jsonElement
            , Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String enumName = jsonElement.getAsString();

        for (HeroInfo heroInfo : HeroInfo.values()) {
            if (heroInfo.name().equals(enumName)) {
                return heroInfo;
            }
        }
        for (UnitCardInfo unitCardInfo : UnitCardInfo.values()) {
            if (unitCardInfo.name().equals(enumName)) {
                return unitCardInfo;
            }
        }
        for (SpecialCardInfo specialCardInfo : SpecialCardInfo.values()) {
            if (specialCardInfo.name().equals(enumName)) {
                return specialCardInfo;
            }
        }
        return jsonDeserializationContext.deserialize(jsonElement,type);
    }

    @Override
    public JsonElement serialize(CardInfo cardInfo, Type type,
                                 JsonSerializationContext jsonSerializationContext) {

        return jsonSerializationContext.serialize(cardInfo);
    }
}
