FERRET  = ferret
INPUT   = core.clj
OUTPUT  = core.ino
ARDUINO = ~/bin/arduino
BOARD   = arduino:avr:uno
PORT    = /dev/ttyACM0
RM      = rm -f

.PHONY: verify upload clean

default: verify

$(OUTPUT): core.clj
	$(FERRET) -o $(OUTPUT)

verify: $(OUTPUT)
	$(ARDUINO) --board $(BOARD) --verify $(OUTPUT)

upload: $(OUTPUT)
	$(ARDUINO) --board $(BOARD) --port $(PORT) --upload $(OUTPUT)

clean:
	$(RM) $(OUTPUT)

serial:
	picocom -b 9600 --imap lfcrlf $(PORT)
