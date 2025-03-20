package community.jessie_community.DTO;

import community.jessie_community.domain.User;

public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String profile_img_url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfile_img_url() {
        return profile_img_url;
    }

    public void setProfile_img_url(String profile_img_url) {
        this.profile_img_url = profile_img_url;
    }

    public static UserDTO fromEntity(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setNickname(user.getNickname());
        dto.setProfile_img_url(user.getProfileImgUrl());

        return dto;
    }

    public static User toEntity(UserDTO dto) {
        User user = new User();
        if (dto.id != null) {
            user.setId(dto.id);
        }
        user.setEmail(dto.email);
        user.setPassword(dto.password);
        user.setNickname(dto.nickname);
        user.setProfileImgUrl(dto.profile_img_url);

        return user;
    }
}
