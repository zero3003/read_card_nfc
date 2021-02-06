#import "ReadCardNfcPlugin.h"
#if __has_include(<read_card_nfc/read_card_nfc-Swift.h>)
#import <read_card_nfc/read_card_nfc-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "read_card_nfc-Swift.h"
#endif

@implementation ReadCardNfcPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftReadCardNfcPlugin registerWithRegistrar:registrar];
}
@end
