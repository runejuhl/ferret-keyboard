(require '[ferret.arduino :as gpio])

(comment
  (configure-ferret! :command "~/projects/golang/bin/arduino-cli upload \\
                               --fqbn arduino:avr:uno \\
                               --port /dev/ttyACM0 \\
                               --verbose \\
                               --input ./blink.cpp"))

(configure-ferret! :command "~/bin/arduino \\
                               --board arduino:avr:uno \\
                               --port /dev/ttyACM0 \\
                               --upload ./blink.cpp")

(gpio/pin-mode 13 :output)

(forever
 (gpio/digital-write 13 1)
 (sleep 500)
 (gpio/digital-write 13 0)
 (sleep 500))
