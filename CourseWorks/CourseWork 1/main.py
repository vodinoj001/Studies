import cv2
import mediapipe as mp

cam = cv2.VideoCapture(0)

hands = mp.solutions.hands.Hands(static_image_mode=False,
                                 max_num_hands=2,
                                 min_tracking_confidence=0.5,
                                 min_detection_confidence=0.5)

mpDraw = mp.solutions.drawing_utils

top_point = [8, 12, 16, 20]
num_fingers_show = 0
tmp = 0

while True:
    _, img = cam.read()
    result = hands.process(img)

    if result.multi_hand_landmarks:
        num_fingers_show = 0
        for hand_landmarks in result.multi_hand_landmarks:
            for id, coordinates in enumerate(hand_landmarks.landmark):
                h, w, _ = img.shape
                cx, cy = int(coordinates.x * w), int(coordinates.y * h)
                cv2.circle(img, (cx, cy), 10, (255, 0, 255))
                mpDraw.draw_landmarks(img, hand_landmarks, mp.solutions.hands.HAND_CONNECTIONS)

                if id == 0:
                    id0_cordin = coordinates

                if id in top_point:
                    if abs(id0_cordin.y - coordinates.y) > 0.3 or abs(id0_cordin.x - coordinates.x) > 0.3:
                        num_fingers_show += 1
                if id == 4:
                    if abs(id0_cordin.y - coordinates.y) > 0.2 or abs(id0_cordin.x - coordinates.x) > 0.2:
                        num_fingers_show += 1

        if num_fingers_show != tmp:
            print(num_fingers_show)
        tmp = num_fingers_show

    cv2.imshow("Hand tracking", img)
    cv2.waitKey(1)
