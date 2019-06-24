package loc.aliar.oppapp.service;

public interface ActivityService {
    void create();

    void update();

    void delete();

    void addModerator(Long activityId, Long userId);

    void getAllSpeakers(Long id);

    boolean isOwner(Long activityId, Long userId);

    boolean canEdit(Long activityId, Long userId);
}
