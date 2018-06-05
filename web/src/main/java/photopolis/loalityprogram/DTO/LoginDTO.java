package photopolis.loalityprogram.DTO;

import photopolis.loalityprogram.model.Bonus;

/**
 * Created by Anatoliy on 05.06.2018.
 */
public class LoginDTO {

    private Boolean authorised;

    private String xored;

    public LoginDTO(Boolean authorised, String xored) {
        this.authorised = authorised;
        this.xored = xored;
    }

    public Boolean getAuthorised() {
        return authorised;
    }

    public LoginDTO setAuthorised(Boolean authorised) {
        this.authorised = authorised;
        return this;
    }

    public String getXored() {
        return xored;
    }

    public LoginDTO setXored(String xored) {
        this.xored = xored;
        return this;
    }
}
