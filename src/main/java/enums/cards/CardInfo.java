package enums.cards;

import java.util.Objects;

public interface CardInfo {
    String getType();
    static CardInfo returnByName(String name){
        for (HeroInfo q : HeroInfo.values()){
            if (Objects.equals(q.toString(), name)){
                return q;
            }
        }
        for (SpecialCardInfo q : SpecialCardInfo.values()){
            if (Objects.equals(q.toString(), name)){
                return q;
            }
        }
        for (UnitCardInfo q : UnitCardInfo.values()){
            if (Objects.equals(q.toString(), name)){
                return q;
            }
        }
        return null;
    }
}
