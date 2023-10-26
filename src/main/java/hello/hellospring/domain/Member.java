package hello.hellospring.domain;

public class Member {
    private Long id; //id 식별자, 고객이 아닌 시스템이 정하는 id임
    private String name; //회원 이름

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
