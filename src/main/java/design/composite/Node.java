package design.composite;

public interface Node {

    void addChild(Node node);
    void removeChild(Node node);
    int sum();
    int getValue();

}
