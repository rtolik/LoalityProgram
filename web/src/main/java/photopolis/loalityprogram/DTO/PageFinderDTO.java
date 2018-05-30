package photopolis.loalityprogram.DTO;

import java.util.List;

/**
 * Created by Anatoliy on 29.05.2018.
 */
public class PageFinderDTO {

    private List<UserPagesDTO> users;

    private Integer numOfPages;

    public PageFinderDTO(List<UserPagesDTO> users, Integer numOfPages) {
        this.users = users;
        this.numOfPages = numOfPages;
    }

    public List<UserPagesDTO> getUsers() {
        return users;
    }

    public PageFinderDTO setUsers(List<UserPagesDTO> users) {
        this.users = users;
        return this;
    }

    public Integer getNumOfPages() {
        return numOfPages;
    }

    public PageFinderDTO setNumOfPages(Integer numOfPages) {
        this.numOfPages = numOfPages;
        return this;
    }
}
