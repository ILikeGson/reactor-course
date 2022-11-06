package reactor.seminar4.model;

import lombok.Getter;

@Getter
public enum Names {
    HIKARU("Hikaru"),
    MAGNUS_CARLSEN("MagnusCarlsen"),
    FIROUZJA_2003("Firouzja2003"),
    DANIEL_NARODITSKY("DanielNaroditsky"),
    ANISH_GIRI("AnishGiri"),
    BIG_FISH_1995("BigFish1995"),
    GRISCHUK("Grischuk"),
    FABIANO_CARUANA("FabianoCaruana"),
    GM_BENJAMIN_BOK("GMBenjaminBok"),
    LACHESIS_Q("lachesisQ");

    private final String name;

    Names(String name) {
        this.name = name;
    }
}
