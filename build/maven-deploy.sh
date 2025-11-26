#!/bin/sh

cat ~/.m2/settings.xml

set
cd /src



mvn --batch-mode deploy
