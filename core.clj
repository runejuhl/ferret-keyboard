(require '[ferret.arduino :as arduino])

(configure-ferret! :command "~/bin/arduino \\
                               --board arduino:avr:uno \\
                               --port /dev/ttyACM0 \\
                               --upload ./blink.cpp")

(arduino/pin-mode 13 :output)
(arduino/pin-mode 7 :output)

(def notes (list 262 294 330 349))
(def speaker 0x8)

(forever
 (let [but1 (arduino/analog-read 0x0)
       note (cond
              (>= but1 1023) (nth notes 0)
              (>= but1 768)  (nth notes 1)
              (>= but1 256)  (nth notes 2)
              (>= but1 1)    (nth notes 3))]
   (println "but1: " but1)
   (println "tone: " note)
   (if tone
     (arduino/tone speaker note)
     (arduino/no-tone speaker))))
